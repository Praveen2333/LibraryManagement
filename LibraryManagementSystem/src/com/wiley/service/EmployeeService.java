package com.wiley.service;

public interface EmployeeService {

	public int employeeLogin(int empId, String empPwd);

	public void insertBook(String bookTitle, int bookId, String bookType, int lateFee, int Stock);

	public void insertEmployee(String empName, int empId, int booksBorrowed, int cardNo, String password);

	public void findBookByName(String bookName, int empid);

	public int inStock(String bookName);

	public void updateStock(String bookName);

	public void displayBooks();

	public void isBookPresent(String bookName);

	public void deleteBook(int eid, int bid);

}
