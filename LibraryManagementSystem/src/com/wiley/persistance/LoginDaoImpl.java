package com.wiley.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {

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
				System.out.println("\nEmployee with id " + empId + " has been Registered successfully");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
