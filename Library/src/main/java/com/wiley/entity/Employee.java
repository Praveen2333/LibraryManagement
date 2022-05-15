package com.wiley.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
@SuppressWarnings("unused")
public class Employee {

	private int empid;
	private String empname;
	private int booksborrowed;
	private int cardno;
	private String password;
}
