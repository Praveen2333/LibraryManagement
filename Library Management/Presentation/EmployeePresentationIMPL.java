package com.wiley.Presentation;

import java.util.Scanner;

import com.wiley.Service.Login;
import com.wiley.Service.LoginIMPL;

public class EmployeePresentationIMPL implements EmployeePresentation {
	private Login login= new LoginIMPL();
	
	@Override
	public void login() { 
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Employee Login Credentials:");
		int empId=scanner.nextInt();
		String Password=scanner.next();
		
		if(login.checkLogin(empId,Password)==true) {
			System.out.println("Login successful");
		}
		else
			System.out.println("Login Failed");

	}
	@Override
	public void showMenu() {
		
	}
	@Override
	public void performChoice(int choice) {
		
	}
		
	}

