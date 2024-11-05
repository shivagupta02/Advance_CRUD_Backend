package com.OnlyApp.OnlyApp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.OnlyApp.OnlyApp.EmpDto.EmpDto;
import com.OnlyApp.OnlyApp.Models.EmployeeEntity;

public interface EmpService {
	
	EmpDto createEmp(EmpDto emp);
	EmpDto updateEmp(EmpDto emp, Integer id );
	EmpDto getEmpById(Integer id);
	Page<EmpDto> getAllEmp( Integer pageNumber, Integer pageSize);
	void deleteEmp(Integer id);
  List<EmployeeEntity> searchByName(String empName);
// List<EmployeeEntity> searchById(Integer id);
  
  // this for criteria api
  
//  List<EmployeeEntity> searchEmployee(String empName, String empEmail, String empMobile);
  List<EmployeeEntity> searchEmployee(String global);
  
}
