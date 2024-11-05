package com.OnlyApp.OnlyApp.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.OnlyApp.OnlyApp.EmpDto.EmpDto;
import com.OnlyApp.OnlyApp.Exception.ResourceNotFoundException;
import com.OnlyApp.OnlyApp.Models.EmployeeEntity;
import com.OnlyApp.OnlyApp.repo.Repository;
import com.OnlyApp.OnlyApp.service.EmpService;
import com.OnlyApp.OnlyApp.specification.EmpSpecification;
@Service
public class EmpResourseServiceImpl implements EmpService  {
	@Autowired
	private Repository empRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EmpSpecification empSpecification;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public EmpDto createEmp(EmpDto emp) {
		 EmployeeEntity employee=this.modelMapper.map(emp, EmployeeEntity.class);
		 if(employee.getAccountDetails()!=null) {
			 
			 employee.getAccountDetails().setEmail(employee.getEmpEmail());
		 }
		 
		// generate password
		 String generatePassword = new String();
		  
		 String name= emp.getEmpName();
		 String mobileNumber= emp.getEmpMobile();
		 
		 if(name!=null && !name.isEmpty()&& mobileNumber!=null&& mobileNumber.length()>=6)
		 {
			 char firstChar=name.charAt(0);
			 String lastSixDigit=mobileNumber.substring(mobileNumber.length()-6);
			 generatePassword=firstChar+lastSixDigit;
		 }
		 
		 if(employee.getAccountDetails()!=null)
		 {
			 employee.getAccountDetails().setPassword(generatePassword);
		 }
//		 if(employee.getAccountDetails().getPassword()!=null)
//		 {
//			 employee.getAccountDetails().setPassword(passwordEncoder.encode(employee.getAccountDetails().getPassword()));
//		 }
		 if(empRepo.findByEmpMobile(employee.getEmpMobile()).isPresent())
		 {
			 throw new IllegalArgumentException("Mobile number is already present!");
		 }
		 if(empRepo.findByEmpEmail(employee.getEmpEmail()).isPresent())
		 {
			 throw new IllegalArgumentException("Email is already present!");
		 }
		 
//		 (employee.getAccountDetails()!=null):employee.getAccountDetails().setEmail(null);
		 EmployeeEntity addEmp=this.empRepo.save(employee);
		return this.modelMapper.map(addEmp, EmpDto.class);
	}

	@Override
	public EmpDto updateEmp(EmpDto emp, Integer id) {
		EmployeeEntity findEmp=this.empRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Employee Id", id));
		
		findEmp.setEmpName(emp.getEmpName());
		findEmp.setEmpEmail(emp.getEmpEmail());
		findEmp.setEmpMobile(emp.getEmpMobile());
		findEmp.setDepartment(emp.getDepartment());
		EmployeeEntity updateEmp=this.empRepo.save(findEmp);
		return this.modelMapper.map(updateEmp, EmpDto.class);
	}

	@Override
	public EmpDto getEmpById(Integer id) {
	   EmployeeEntity find=this.empRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Employee Id", id));
		return this.modelMapper.map(find, EmpDto.class);
	}

	@Override
	public Page<EmpDto> getAllEmp(Integer pageNumber, Integer pageSize) {
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<EmployeeEntity> page=this.empRepo.findAll(p);
		Page<EmpDto> collect = page.map((e)-> this.modelMapper.map(e, EmpDto.class));
//		List<EmpDto> collect= getAll.stream().map((e)-> this.modelMapper.map(e, EmpDto.class)).toList();
		return collect;
	}

	@Override
	public void deleteEmp(Integer id) {
		EmployeeEntity delete=this.empRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Employee Id", id));
        this.empRepo.delete(delete);		
	}

	@Override
	public List<EmployeeEntity> searchByName(String empName) {
		
		return empRepo.findByEmpNameContaining(empName);
	}

	@Override
	public List<EmployeeEntity> searchEmployee(String global) {
		Specification<EmployeeEntity> specification=Specification.where(null);
				specification=specification
				.and(empSpecification.filterByName(global))
				.or(empSpecification.filterByEmail(global))
				.or(empSpecification.filterByMobile(global));
		return empRepo.findAll(specification);
	}

//	@Override
//	public List<EmployeeEntity> searchById(Integer id) {
//		String sid=String.valueOf(id); 
//				return this.empRepo.findByIdContaining(sid);
//	}




}
