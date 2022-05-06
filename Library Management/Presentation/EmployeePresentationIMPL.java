package com.wiley.Presentation;

import com.wiley.Service.Login;
import com.wiley.Service.LoginIMPL;

public class EmployeePresentationIMPL implements EmployeePresentation {
	private Login login= new LoginIMPL();
	@Override
	public boolean checkLogin(int EmpId, String Password) {
		if(login.checkLogin(EmpId, Password)==true)
			return true;
		return false;
	}
	@Override
	public void showMenu() {
		
	}
	@Override
	public void performChoice(int choice) {
		
	}

}
