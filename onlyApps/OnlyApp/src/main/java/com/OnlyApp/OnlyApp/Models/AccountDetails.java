package com.OnlyApp.OnlyApp.Models;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
public class AccountDetails implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator", sequenceName = "account-sequence", allocationSize = 1)
	@Column(name = "account_id")
	private Integer id;
	
	@NotBlank
	@Email(message = "Email is not valid")
	@Column(name="Email")
	private String email;
	
	@Size(min = 8, message = "Password must be atleast 8 character")
	@Column(name = "Password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToOne(mappedBy = "accountDetails")
//	@JoinColumn(name="employee_id", referencedColumnName = "Emp_Id")
	private EmployeeEntity employeeEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password =encoder.encode(password);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}

	public AccountDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public AccountDetails(Integer id, String email, String password, Role role, EmployeeEntity employeeEntity) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.employeeEntity = employeeEntity;
	}

	@Override
	public String toString() {
		return "AccountDetails [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", employeeEntity=" + employeeEntity + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 Set<SimpleGrantedAuthority> authorties = Collections.singleton(new SimpleGrantedAuthority("ROLE_"+role));
		 return authorties;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true ;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true ;
	}

	
	

}
