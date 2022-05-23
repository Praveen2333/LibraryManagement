package com.wiley.persistance;

public interface LoginDao {
	
	public int employeeLogin(int id, String pwd);

	public void insertEmployee(String empName, int empId, int booksBorrowed, int cardNo, String password);

}
