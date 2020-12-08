package com.revature.models;

import java.util.Date;

public class Employee extends User {

	int annualSalary;
	Date startDate;
	int vacationsHours;
	int employeeNumber;
	
	public Employee(int id, String username, String password) {
		super(id, username, password);
		int annualSalary = 0;
		Date startDate = new Date();
		int vacationsHours = 0;
		int employeeNumber = 0;
	}
	
	public void approveAccount(Customer c, boolean isApproved){
		
	}
	
	public long viewBalance(Customer c) {
		return 0L;
	}
	
	//add time as a parameter
	public String viewLogs() {
		return "LOGS";
	}

	public User login() {
		// TODO Auto-generated method stub
		return null;
	}

	public User registerNewAcount() {
		// TODO Auto-generated method stub
		return null;
	}

}
