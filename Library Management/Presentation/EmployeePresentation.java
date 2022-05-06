package com.wiley.Presentation;

public interface EmployeePresentation {
	boolean checkLogin(int EmpId,String Password);
	
	void showMenu();
	void performChoice(int choice);
}
