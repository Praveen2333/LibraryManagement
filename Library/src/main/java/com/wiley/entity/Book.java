package com.wiley.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
@SuppressWarnings("unused")
public class Book {

	private String bookTitle;
	private int bookId;
	private String bookType;
	private int lateFee;
	private int stock;
}
