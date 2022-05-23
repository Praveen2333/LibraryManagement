package com.wiley.service;

import com.wiley.persistance.LoginDao;
import com.wiley.persistance.LoginDaoImpl;

public class LoginServiceImpl implements LoginService {
	LoginDao loginDao = new LoginDaoImpl();

	@Override
	public int employeeLogin(int empId, String empPwd) {
		int result = loginDao.employeeLogin(empId, empPwd);
		return result;
	}


	@Override
	public void insertEmployee(String empName, int empId, int booksBorrowed, int cardNo, String password) {
		loginDao.insertEmployee(empName, empId, booksBorrowed, cardNo, password);
		
	}

}
