package com.revature.repositories;

import java.util.HashMap;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public class FakeFileDB {
	
	static HashMap<String, User> db = new HashMap<>();
	
	static {
		Customer cust1 = new Customer(0, "kyle", "g");
		Customer cust2 = new Customer(1, "erica", "s");
		Employee emp1 = new Employee(2, "kurt", "g");
		Employee emp2 = new Employee(3, "colin", "s");
		db.put(cust1.getUsername(), cust1);
		db.put(cust2.getUsername(), cust2);
		db.put(emp1.getUsername(), emp1);
		db.put(emp2.getUsername(), emp2);

		cust1.addAccount(new Account(100,0));
		cust1.addAccount(new Account(200,1));
		
		cust2.addAccount(new Account(1000,0));
		cust2.addAccount(new Account(2000,1));

	}
}
