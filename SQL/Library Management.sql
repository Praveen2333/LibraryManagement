create database LibraryManagement;
use LibraryManagement;
create table EMPLOYEE(EMPLOYEE_ID int not null,
EMPLOYEE_NAME varchar(30),
DEPARTMENT varchar(30),
DESIGNATION varchar(30),
SALARY double,
QUANTITY int ,
LOGIN_PASSWORD varchar(30),
primary key(EMPLOYEE_ID));

insert into employee values(101,"AKHIL","HR","MANAGER",20000,0,"akhil@123"),(102,"SHIVAM","R&D","Executive",50000,0,"shivam@123");
