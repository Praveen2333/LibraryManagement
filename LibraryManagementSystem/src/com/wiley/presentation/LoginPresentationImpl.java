package com.wiley.presentation;

import java.util.Scanner;

import com.wiley.service.LoginService;
import com.wiley.service.LoginServiceImpl;

public class LoginPresentationImpl implements LoginPresentation {

	@Override
	public void showMenu() {
		System.out.println("\n1. Already Having an Account");
		System.out.println("2. Create an Account");
	}
	
	@Override
	public int performChoice(int choice) {	
		LoginService loginService = new LoginServiceImpl();
		LoginPresentation loginPresentation = new LoginPresentationImpl();
		EmployeePresentation employeePresentation=new EmployeePresentationImpl();
		Scanner scanner = new Scanner(System.in);
		switch (choice) {
		case 1:
			System.out.println("Enter Employee ID : ");
			int employeeId = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter your password");
			String employeePassword = scanner.nextLine();
			int success = loginService.employeeLogin(employeeId, employeePassword);
			if(success==1) {
				System.out.println("Logged in Successfully");
				while (true) {
					employeePresentation.showMenu();
					System.out.println("Enter Choice : ");
					int select = Integer.parseInt(scanner.nextLine());
					employeePresentation.performChoice(select,employeeId);
					}
			}
			else {
				System.out.println("Login Failed....");
			}
			break;
		case 2:
			System.out.println("Enter the details of the Employee");
			System.out.println("------------------------------");
			System.out.println("Please Enter your Name");
			String empName = scanner.nextLine();
			System.out.println("Please Enter your Id");
			int empId = Integer.parseInt(scanner.nextLine());
			System.out.println("Please Enter no of books borrowed");
			int booksBorrowed = Integer.parseInt(scanner.nextLine());
			System.out.println("Please Enter your Card Number");
			int cardNo = Integer.parseInt(scanner.nextLine());
			System.out.println("Please Enter your Password");
			String password = scanner.nextLine();
			loginService.insertEmployee(empName, empId, booksBorrowed, cardNo, password);
			loginPresentation.showMenu();
			System.out.println("Enter Choice : ");
			int option = Integer.parseInt(scanner.nextLine());
			loginPresentation.performChoice(option);
			break;		
		default:
		System.out.println("Invalid Choice");
		System.exit(0);
		break;
	}
		return choice;

}

}
