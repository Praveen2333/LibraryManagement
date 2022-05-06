package com.wiley.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class Employee {
	private int empId;
	private String empName;
	private String empDepartment;
	private String empDesignation;
	private double empSalary;
	private int Quantity;
	private String empPassword;
}
