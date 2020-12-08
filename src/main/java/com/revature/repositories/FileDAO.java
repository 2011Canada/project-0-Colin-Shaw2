package com.revature.repositories;


import java.util.HashSet;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public class FileDAO implements UserDAO {
	
	static HashSet<User> db = new HashSet<>();
	
	static {
		Customer cust1 = new Customer(1, "kyle", "g");
		Customer cust2 = new Customer(1, "colin", "s");
		Employee emp1 = new Employee(1, "kurt", "g");
		Employee emp2 = new Employee(1, "erica", "s");
		db.add(cust1);
		db.add(cust2);
		db.add(emp1);
		db.add(emp2);

	}

	public FileDAO() {
		// TODO Auto-generated constructor stub
	}

	public Customer addCustomer(Customer u) {
		
		return null;
	}

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	public User[] findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public User findUserByName(String s) {
		for(User u : db) {
			if(s.equals(u.getUsername())) {
				return u;
			}
		}
		return null;
	}

}
