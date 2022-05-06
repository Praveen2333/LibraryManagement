package com.wiley.User;

import java.util.Scanner;

import com.wiley.Presentation.EmployeePresentation;
import com.wiley.Presentation.EmployeePresentationIMPL;

public class User {

	public static void main(String[] args) {
		EmployeePresentation employeePresentation=new EmployeePresentationIMPL();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Employee Login Credentials:");
		int empId=scanner.nextInt();
		String Password=scanner.next();
		
		if(employeePresentation.checkLogin(empId,Password)==true) {
			System.out.println("Login successful");
			employeePresentation.showMenu();
			System.out.println("Enter Choice : ");
			int choice=scanner.nextInt();
			employeePresentation.performChoice(choice);
			
		}
		else
			System.out.println("Login Failed");

	}

}
