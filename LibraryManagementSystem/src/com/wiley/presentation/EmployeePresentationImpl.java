package com.wiley.presentation;

import java.util.Scanner;

import com.wiley.service.EmployeeService;
import com.wiley.service.EmployeeServiceImpl;

public class EmployeePresentationImpl implements EmployeePresentation {
	
	
	@Override
	public void showMenu() {
		System.out.println("\n1. Insert Book details");
		System.out.println("2. Issued a book");
		System.out.println("3. Return the issued book");
		System.out.println("4. Books Taken By Employee");
		System.out.println("5. Exit");

	}

	@Override
	public void performChoice(int choice,int EmpId) {
		Scanner scanner = new Scanner(System.in);
		EmployeeService employeeService = new EmployeeServiceImpl();
		switch (choice) {
		case 1:
			
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
			
		case 2:
			System.out.println("The available books are:");
			employeeService.displayBooks();
			System.out.println("Please Enter book Name");
			String bookName = scanner.nextLine();
			employeeService.isBookPresent(bookName);
			if(employeeService.BooksLimit(EmpId)) {
				if(employeeService.BookExisted(bookName, EmpId)) {
					int available = employeeService.inStock(bookName);
					if (available > 0) {
						System.out.println("Your Book is Available");
						System.out.println("Do you wish to take the book(Yes/No)");
						String choose = scanner.nextLine();
						if (choose.equalsIgnoreCase("Yes")) {
							employeeService.findBookByName(bookName, EmpId);
							if (available > 0) {
								employeeService.updateStock(bookName);
							}
						} 
						else {
							System.out.println("Thank you");
						}

					} else {	
						System.out.println("Your Book is out of Stock, Sorry!");
					}
				}
				else {
				System.out.println("Book is Already Taken");
				}
			}
			else {
				System.out.println("Books Limit Reached");
			}
			break;
			
		case 3:
			System.out.println("Enter your Book Id");
			int bookid=Integer.parseInt(scanner.nextLine());
			employeeService.deleteBook(EmpId,bookid);
			break;
			
		case 4:
			System.out.println("Book taken By Employee: "+EmpId+" .");
			employeeService.showbookstaken(EmpId);
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
