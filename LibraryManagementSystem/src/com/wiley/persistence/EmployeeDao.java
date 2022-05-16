package com.wiley.persistence;

public interface EmployeeDao {
	public int employeeLogin(int id, String pwd);

	public void insertBook(String bookTitle, int bookId, String bookType, int lateFee, int Stock);

	public void insertEmployee(String empName, int empId, int booksBorrowed, int cardNo, String password);

	public void findBookByName(String bookName, int empid);

	public int inStock(String bookName);

	public void updateStock(String bookName);

	public void displayBooks();

	public void isBookPresent(String bookName);

	public void deleteBook(int eid, int bid);
	
	
}
