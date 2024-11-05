package com.OnlyApp.OnlyApp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlyApp.OnlyApp.EmpDto.EmpDto;
import com.OnlyApp.OnlyApp.Models.EmployeeEntity;
import com.OnlyApp.OnlyApp.repo.Repository;
import com.OnlyApp.OnlyApp.serviceImpl.EmpResourseServiceImpl;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpController {

	@Autowired
	private EmpResourseServiceImpl empServiceImpl;
	
	@Autowired
	private Repository repo;

	@PostMapping("/createemp")
	public ResponseEntity<?> createdEmp(@Valid @RequestBody EmpDto emp) throws ConstraintViolationException {
		
		
		
		
		EmpDto createEmpDto = this.empServiceImpl.createEmp(emp);
		return new ResponseEntity<>(createEmpDto, HttpStatus.CREATED);
		
			
		
		}

	@PutMapping("/{empid}")
	public ResponseEntity<EmpDto> updateEmploye(@Valid @RequestBody EmpDto emp, @PathVariable("empid") Integer id) {
		EmpDto update = this.empServiceImpl.updateEmp(emp, id);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}

	@GetMapping("/getAllEmp")
	public ResponseEntity<Page<EmpDto>> getallEmp(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
		return ResponseEntity.ok(this.empServiceImpl.getAllEmp(pageNumber, pageSize));
	}

	@GetMapping("/{empid}")
	public ResponseEntity<EmpDto> getById(@PathVariable("empid") Integer id) {
		EmpDto getEmp = this.empServiceImpl.getEmpById(id);
		return ResponseEntity.ok(getEmp);
	}

	@DeleteMapping("{empid}")
	public String deleteEmp(@PathVariable("empid") Integer id) {
		this.empServiceImpl.deleteEmp(id);
		return "Deleted succesfully";

	}

	@GetMapping("/search")
	public List<EmployeeEntity> searchEmployees(@RequestParam(name = "name") String empName) {
		return empServiceImpl.searchByName(empName);
	}

	@GetMapping("/globalsearch")
	public List<EmployeeEntity> globalSearch(@RequestParam(name = "global") String global) {
		return empServiceImpl.searchEmployee(global);

	}
//	 @GetMapping("/search")
//	 public List<EmployeeEntity> searchById(@RequestParam(name="id") Integer id){
//		 return empServiceImpl.searchById(id);
//	 }
}
