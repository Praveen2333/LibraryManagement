package com.wiley.service;

public interface LoginService {
	
	public int employeeLogin(int empId, String empPwd);
	
	public void insertEmployee(String empName, int empId, int booksBorrowed, int cardNo, String password);
	
}
