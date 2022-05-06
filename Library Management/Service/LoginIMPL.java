package com.wiley.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginIMPL implements Login {

	@Override
	public boolean checkLogin(int EmpId, String Password) {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3000/librarymanagement","root","wiley");
			String sql="SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID=? and LOGIN_PASSWORD=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, EmpId);
			statement.setString(2, Password);
			ResultSet result=statement.executeQuery();
			if(result.next()) {
				return true;
			}
			else
				return false;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	
}
