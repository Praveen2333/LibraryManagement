package com.wiley.client;

import java.util.Scanner;

import com.wiley.presentation.EmployeePresentation;
import com.wiley.presentation.EmployeePresentationImpl;

public class EmployeeClient {

	public static void main(String[] args) {

		EmployeePresentation employeePresentation = new EmployeePresentationImpl();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			employeePresentation.showMenu();
			System.out.println("Enter Choice : ");
			int choice = Integer.parseInt(scanner.nextLine());
			employeePresentation.performChoice(choice);

		}
	}

}
