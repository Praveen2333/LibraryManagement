package com.wiley.service;

import com.wiley.persistance.EmployeeDao;
import com.wiley.persistance.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao = new EmployeeDaoImpl();

	@Override
	public void insertBook(String bookTitle, int bookId, String bookType, int lateFee, int stock) {
		employeeDao.insertBook(bookTitle, bookId, bookType, lateFee, stock);

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

	@Override
	public boolean BookExisted(String BookName, int EmpId) {
		return employeeDao.BookExisted(BookName, EmpId);
		
		
	}

	@Override
	public boolean BooksLimit(int EmpId) {
		return employeeDao.BooksLimit(EmpId);
	}

	@Override
	public void showbookstaken(int EmpId) {
		employeeDao.showbookstaken( EmpId);
		
	}


}
