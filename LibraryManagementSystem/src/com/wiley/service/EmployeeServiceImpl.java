package com.wiley.service;

import com.wiley.persistence.EmployeeDao;
import com.wiley.persistence.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao = new EmployeeDaoImpl();

	@Override
	public int employeeLogin(int empId, String empPwd) {
		int result = employeeDao.employeeLogin(empId, empPwd);
		return result;
	}

	@Override
	public void insertBook(String bookTitle, int bookId, String bookType, int lateFee, int stock) {
		employeeDao.insertBook(bookTitle, bookId, bookType, lateFee, stock);

	}

	@Override
	public void insertEmployee(String empName, int empId, int booksBorrowed, int cardNo, String password) {
		employeeDao.insertEmployee(empName, empId, booksBorrowed, cardNo, password);

	}

	@Override
	public void findBookByName(String bookName, int empid) {
		employeeDao.findBookByName(bookName, empid);

	}

	@Override
	public int inStock(String bookName) {
		int result = employeeDao.inStock(bookName);
		return result;
	}

	@Override
	public void updateStock(String bookName) {
		employeeDao.updateStock(bookName);
	}

	@Override
	public void displayBooks() {
		employeeDao.displayBooks();

	}

	@Override
	public void isBookPresent(String bookName) {
		 employeeDao.isBookPresent(bookName);

	}

	@Override
	public void deleteBook(int eid, int bid) {
		employeeDao.deleteBook(eid,bid);
		
	}


}
