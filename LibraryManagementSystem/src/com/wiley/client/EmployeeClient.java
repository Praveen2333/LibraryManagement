package com.wiley.client;

import java.util.Scanner;

import com.wiley.presentation.LoginPresentation;
import com.wiley.presentation.LoginPresentationImpl;

public class EmployeeClient {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LoginPresentation loginPresentation = new LoginPresentationImpl();
			loginPresentation.showMenu();
			System.out.println("Enter Choice : ");
			int choice = Integer.parseInt(scanner.nextLine());
			loginPresentation.performChoice(choice);

	}

}
