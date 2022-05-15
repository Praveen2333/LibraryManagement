package com.wiley.presentation;

import java.util.Scanner;

import com.wiley.service.EmployeeService;
import com.wiley.service.EmployeeServiceImpl;

public class EmployeePresentationImpl implements EmployeePresentation {

	@Override
	public void showMenu() {
		System.out.println("\n1. Return the issued book");
		System.out.println("2. Insert Book details");
		System.out.println("3. Insert Employee details");
		System.out.println("4. Issue a book");
		System.out.println("5. Exit");

	}

	@Override
	public void performChoice(int choice) {
		Scanner scanner = new Scanner(System.in);
		EmployeeService employeeService = new EmployeeServiceImpl();
		switch (choice) {
		case 1:
			System.out.println("Enter your Employee Id");
			int eid=Integer.parseInt(scanner.nextLine());
			System.out.println("Enter your Book Id");
			int bid=Integer.parseInt(scanner.nextLine());
			employeeService.deleteBook(eid,bid);
			break;
		case 2:
			System.out.println("Enter Employee ID : ");
			int employeeId = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter your password");
			String employeePassword = scanner.nextLine();
			int success = employeeService.employeeLogin(employeeId, employeePassword);
			if (success == 1) {
				System.out.println("Login successful\n");
				System.out.println("Enter the details of the book");
				System.out.println("------------------------------");
				System.out.println("Please Enter book Title");
				String bookTitle = scanner.nextLine();
				System.out.println("Please Enter book Id");
				int bookId = Integer.parseInt(scanner.nextLine());
				String bookType = "";
				int lateFee = 0;
				System.out.println("Please Select book Type");
				System.out.print("1. Data Analytics\n2. Technology\n3. Management\n");
				int option = Integer.parseInt(scanner.nextLine());
				if (option == 1) {
					bookType = "Data Analytics";
					lateFee = 5;
				} else if (option == 2) {
					bookType = "Technology";
					lateFee = 6;
				} else if (option == 3) {
					bookType = "Management";
					lateFee = 7;
				} else {
					System.out.println("Invalid book Type choosed");
					break;
				}
				System.out.println("Please Enter the the available Stock");
				int stock = Integer.parseInt(scanner.nextLine());
				employeeService.insertBook(bookTitle, bookId, bookType, lateFee, stock);
				break;
			} else {
				System.out.println("Login Failed, Please check your credentials");
				break;
			}
		case 3:
			System.out.println("Enter the details of the Employee");
			System.out.println("------------------------------");
			System.out.println("Please Enter your Name");
			String empName = scanner.nextLine();
			System.out.println("Please Enter your Id");
			int empId = Integer.parseInt(scanner.nextLine());
			// scanner.nextLine();
			System.out.println("Please Enter no of books borrowed");
			int booksBorrowed = Integer.parseInt(scanner.nextLine());
			System.out.println("Please Enter your Card Number");
			int cardNo = Integer.parseInt(scanner.nextLine());
			System.out.println("Please Enter your Password");
			String password = scanner.nextLine();
			employeeService.insertEmployee(empName, empId, booksBorrowed, cardNo, password);
			break;
		case 4:
			System.out.println("Please Enter your Employee Id");
			int empid = Integer.parseInt(scanner.nextLine());
			System.out.println("Please Enter your password");
			String emppassword = scanner.nextLine();
			int result = employeeService.employeeLogin(empid, emppassword);
			if (result == 1) {
				System.out.println("Welcome logged in Successfully");
				System.out.println("The available books are:");
				employeeService.displayBooks();
				System.out.println("Please Enter book Name");
				String bookName = scanner.nextLine();
				employeeService.isBookPresent(bookName);
				int available = employeeService.inStock(bookName);
				if (available > 0) {
					System.out.println("Your Book is Available");
					System.out.println("Do you wish to take the book(Yes/No)");
					String choose = scanner.nextLine();
					if (choose.equalsIgnoreCase("Yes")) {
						employeeService.findBookByName(bookName, empid);
						if (available > 1) {
							employeeService.updateStock(bookName);
						}
					} else {
						System.out.println("Thank you");
					}

				} else {
					System.out.println("Your Book is not available, Sorry!");
				}

			} else {
				System.out.println("Login Failed, Please check your credentials");
			}
			break;
		case 5:
			System.out.println("Thanks for using Library Management System");
			System.exit(0);
		default:
			System.out.println("Invalid Choice");
			break;
		}

	}

}
