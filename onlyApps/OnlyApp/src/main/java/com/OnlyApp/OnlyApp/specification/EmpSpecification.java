package com.OnlyApp.OnlyApp.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.OnlyApp.OnlyApp.Models.EmployeeEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class EmpSpecification {

	public Specification<EmployeeEntity> filterByName(String global ) {
		return new Specification<EmployeeEntity>() {
			@Override
			public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return global == null ? criteriaBuilder.conjunction()
						: criteriaBuilder.like(root.get("empName"), "%"+global+"%");
			}
		};
	}
	
	public Specification<EmployeeEntity> filterByEmail(String global) {
		return new Specification<EmployeeEntity>() {
			@Override
			public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return global == null ? criteriaBuilder.conjunction()
						: criteriaBuilder.like(root.get("empEmail"), "%"+global+"%");
			}
		};
	}
	
	public Specification<EmployeeEntity> filterByMobile(String global) {
		return new Specification<EmployeeEntity>() {
			@Override
			public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return global == null ? criteriaBuilder.conjunction()
						: criteriaBuilder.like(root.get("empMobile"), "%"+global+"%");
			}
		};
	}
}
