package com.OnlyApp.OnlyApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity 
@Table(name="EmployeeDetails")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator", sequenceName = "emp-sequence", allocationSize = 1)
	
	
	@Column(name = "Emp_Id")
	private Integer id;
	
	@NotBlank
	@Size(min = 3, max = 20, message = "Name must be between 3 to 20 character!!")
	@Column(name = "Emp_Name")
	private String empName;
	
	
	@Column(name = "Emp_Email")
	private String empEmail;
	
	@Column (name="Emp_Mobile")
	private String empMobile;
	
	
	@Column(name="Emp_Department")
	private String department;
	
	@JsonIgnoreProperties("employeeEntity")
	@OneToOne(cascade = CascadeType.ALL)
	private AccountDetails accountDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	public EmployeeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", empName=" + empName + ", empEmail=" + empEmail + ", empMobile="
				+ empMobile + ", department=" + department + "]";
	}

	
}
