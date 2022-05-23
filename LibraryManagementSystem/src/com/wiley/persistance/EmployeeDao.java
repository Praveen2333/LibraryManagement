package com.wiley.persistance;

public interface EmployeeDao {

	public void insertBook(String bookTitle, int bookId, String bookType, int lateFee, int Stock);

	public void findBookByName(String bookName, int empid);

	public int inStock(String bookName);

	public void updateStock(String bookName);

	public void displayBooks();

	public void isBookPresent(String bookName);

	public void deleteBook(int eid, int bid);
	
	public boolean BookExisted(String BookName,int EmpId);
	
	public boolean BooksLimit(int EmpId);
	
	public void showbookstaken(int EmpId);
}
