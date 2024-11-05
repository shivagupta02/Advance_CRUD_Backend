package com.OnlyApp.OnlyApp.Models;

public class ApiResponce {
	
	private  String message;
	private Boolean success;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public ApiResponce(String message, Boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public ApiResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
