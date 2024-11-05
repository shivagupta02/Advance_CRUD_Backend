package com.OnlyApp.OnlyApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.OnlyApp.OnlyApp.Models.EmployeeEntity;

public interface Repository extends JpaRepository<EmployeeEntity, Integer>,JpaSpecificationExecutor<EmployeeEntity>{
	List<EmployeeEntity> findByEmpNameContaining(String empName);
//	List<EmployeeEntity> findByIdContaining(String id);
	Optional<EmployeeEntity> findByEmpMobile(String empMobile);
	Optional<EmployeeEntity> findByEmpEmail(String empEmail);
	
}
