package com.wiley.User;

import java.util.Scanner;

import com.wiley.Presentation.EmployeePresentation;
import com.wiley.Presentation.EmployeePresentationIMPL;

public class User {

	public static void main(String[] args) {
		EmployeePresentation employeePresentation=new EmployeePresentationIMPL();
		employeePresentation.login();

}
}

