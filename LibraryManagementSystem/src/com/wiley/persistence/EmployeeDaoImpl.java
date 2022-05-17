package com.wiley.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class EmployeeDaoImpl implements EmployeeDao {

	static int sno=0;
	@Override
	public int employeeLogin(int id, String pwd) {
		int result = 0;
		String retrievedPwd = "";
		
		PreparedStatement preparedStatement = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("Select password from Employee where empid=?  ");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				retrievedPwd = resultSet.getString(1);
			}
			if (pwd.equals(retrievedPwd)) {
				result = 1;
			} else
				result = 0;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insertBook(String bookTitle, int bookId, String bookType, int lateFee, int stock) {
		PreparedStatement preparedStatement = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("insert into book values(?,?,?,?,?)");
			preparedStatement.setString(1, bookTitle);
			preparedStatement.setInt(2, bookId);
			preparedStatement.setString(3, bookType);
			preparedStatement.setInt(4, lateFee);
			preparedStatement.setInt(5, stock);
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println("\nBook with id " + bookId + " has been inserted successfully");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insertEmployee(String empName, int empId, int booksBorrowed, int cardNo, String password) {
		PreparedStatement preparedStatement = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("insert into employee values(?,?,?,?,?)");
			preparedStatement.setString(1, empName);
			preparedStatement.setInt(2, empId);
			preparedStatement.setInt(3, booksBorrowed);
			preparedStatement.setInt(4, cardNo);
			preparedStatement.setString(5, password);
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println("\nEmployee with id " + empId + " has been inserted successfully");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void findBookByName(String bookName, int empid) {
		sno++;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		PreparedStatement preparedStatement4=null;
		String empName = "";
		int cardNo = 0, bookId = 0, lateFee = 0;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement1 = connection.prepareStatement("select * from book where booktitle=?");
			preparedStatement1.setString(1, bookName);
			ResultSet resultSet1 = preparedStatement1.executeQuery();
			preparedStatement2 = connection.prepareStatement("select * from employee where empid=?");
			preparedStatement2.setInt(1, empid);
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			if (resultSet2.next()) {
				empName = resultSet2.getString("empname");
				cardNo = resultSet2.getInt("cardno");
			}
			if (resultSet1.next()) {
				bookId = resultSet1.getInt("bookid");
				lateFee = resultSet1.getInt("latefee");
			}
			long nowIssue = System.currentTimeMillis();
			Date issueDate = new Date(nowIssue);
			String issued = issueDate.toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(issued));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.add(Calendar.DATE, 7); 
			String returnDate = sdf.format(c.getTime());
			preparedStatement3 = connection.prepareStatement("insert into issued values(?,?,?,?,?,?,?,?)");
			preparedStatement3.setInt(1,sno);
			preparedStatement3.setString(2, empName);
			preparedStatement3.setInt(3, empid);
			preparedStatement3.setInt(4, bookId);
			preparedStatement3.setInt(5, cardNo);
			preparedStatement3.setDate(6, issueDate);
			preparedStatement3.setString(7, returnDate);
			preparedStatement3.setInt(8, lateFee);
			int result = preparedStatement3.executeUpdate();
			if (result == 1) {
				System.out.println("\nBook has been successfully Issued to " + empName);
			}
			preparedStatement4 = connection.prepareStatement("update employee set booksborrowed=booksborrowed+1 where empid=?");
			preparedStatement4.setInt(1, empid);
			preparedStatement4.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int inStock(String bookName) {
		PreparedStatement preparedStatement = null;
		int available = 0;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("select * from book where booktitle=?");
			preparedStatement.setString(1, bookName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				available = resultSet.getInt("stock");
			}
			if (available > 0) {
				return available;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void updateStock(String bookName) {
		PreparedStatement preparedStatement = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("update book set stock=stock-1 where booktitle=?");
			preparedStatement.setString(1, bookName);
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println("Stock has been Successfully updated");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void displayBooks() {
		PreparedStatement preparedStatement = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("select * from book");
			int k = 1;
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(k + ". " + resultSet.getString(1)+ "("+resultSet.getInt(5)+")");
				k++;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void isBookPresent(String bookName) {
		PreparedStatement preparedStatement = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("select booktitle from book");
			int k = 0;
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString(1).equalsIgnoreCase(bookName))
					k = 1;
			}
			if (k == 0)
				System.out.println("Book Not Found");
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteBook(int eid, int bid) {
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		PreparedStatement preparedStatement4 = null;
		try {
			String issueDate = "";
			String returnDate = "";
			String empName = "";
			int lateFee = 0;
			String bookType = "";
			java.util.Date start = null;
			java.util.Date end = null;
			long difference_In_Days = 0;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String currentDate = dtf.format(now);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement2 = connection.prepareStatement("Select * from issued where empid=? and bookid=?");
			preparedStatement2.setInt(1, eid);
			preparedStatement2.setInt(2, bid);
			preparedStatement3 = connection.prepareStatement("Select * from book where bookid=?");
			preparedStatement3.setInt(1, bid);
			ResultSet resultSet3 = preparedStatement3.executeQuery();
			if (resultSet3.next()) {
				bookType = resultSet3.getString("booktype");
			}
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			while (resultSet2.next()) {
				issueDate = resultSet2.getDate("issuedate").toString();
				returnDate = resultSet2.getString("expectedreturn").toString();
				empName = resultSet2.getString("empname");
				lateFee = resultSet2.getInt("latefee");
			}
			try {
				start = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(currentDate);
				end = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				if (start.compareTo(end) > 0) {
					System.out.println("Sorry you are late and you should pay your fine");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date d1 = sdf.parse(currentDate);
					java.util.Date d2 = sdf.parse(returnDate);
					long difference_In_Time = d2.getTime() - d1.getTime();
					difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
				} else if (start.compareTo(end) <= 0) {
					System.out.println("Good There is no late fee applicable");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int fine = 0;
			if (difference_In_Days != 0) {
				fine = (int) (lateFee * difference_In_Days * -1);
			}
			System.out.println("Name of the Employee: " + empName);
			System.out.println("Type of the Book: " + bookType);
			System.out.println("Date of Issue: " + issueDate);
			System.out.println("Date of Return: " + returnDate);
			System.out.println("Late Fee: " + fine);
			preparedStatement1 = connection.prepareStatement("delete from issued where empid=? and bookid=?");
			preparedStatement1.setInt(1, eid);
			preparedStatement1.setInt(2, bid);
			preparedStatement1.executeUpdate();
			preparedStatement4 = connection.prepareStatement("update book set stock=stock+1 where bookid=?");
			preparedStatement4.setInt(1, bid);
		    preparedStatement4.executeUpdate();
			preparedStatement4 = connection.prepareStatement("update employee set booksborrowed=booksborrowed-1 where empid=?");
			preparedStatement4.setInt(1, eid);
			preparedStatement4.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean BookExisted(String BookName, int EmpId) {
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			preparedStatement = connection.prepareStatement("select * from book where booktitle=?");
			preparedStatement.setString(1, BookName);
			preparedStatement1 = connection.prepareStatement("select * from issued where empId=?");
			preparedStatement1.setInt(1, EmpId);
			int k=0,l=0,In=0;
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSet resultSet1 = preparedStatement1.executeQuery();
			if (resultSet.next()) {
				k = resultSet.getInt("BookId");
			}
			while (resultSet1.next()) {
				if (resultSet1.getInt("BookId")==k)
					In=1;
			}
			if (In==1)
				return false;
			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public boolean BooksLimit() {
		PreparedStatement preparedStatement = null;
		
		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/Library", "root",
					"wiley");
			Class.forName("com.mysql.cj.jdbc.Driver");
			int k=0;
			preparedStatement = connection.prepareStatement("select * from employee ");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("BooksBorrowed")<3)
					k=1;
			}
			if (k==1)
				return true;
			return false;
				

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
