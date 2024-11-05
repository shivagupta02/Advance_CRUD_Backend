package com.OnlyApp.OnlyApp.EmpDto;

import com.OnlyApp.OnlyApp.Models.AccountDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmpDto {
	
	private Integer id;
	
	
	private String empName;
		
	@NotBlank
	@Email(message = "Email is Invalid.")
	private String empEmail;
	
	@NotBlank
	@Size(min = 10, max = 10, message = "Number must be 10 digits")
	@Pattern(regexp =  "(^[6-9]{1}[0-9]*$)", message = "Number must be start with 6 - 9 ")
	private String empMobile;
	
	@NotBlank
	@Size(min = 2, max = 20, message = " Department must be between 2 to 20 character ")	
	private String department;

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

//	public EmpDto(Integer id, String empName, String empEmail, String empMobile, String department) {
//		super();
//		this.id = id;
//		this.empName = empName;
//		this.empEmail = empEmail;
//		this.empMobile = empMobile;
//		this.department = department;
//	}

	public EmpDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	
}
