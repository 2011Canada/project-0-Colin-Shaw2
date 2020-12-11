package com.revature.models;

import java.util.Date;

public class Employee extends User {

//	int annualSalary;
//	Date startDate;
//	int vacationsHours;
//	int employeeNumber;
	
	public Employee(String username, String password) {
		super(username, password);
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


}
