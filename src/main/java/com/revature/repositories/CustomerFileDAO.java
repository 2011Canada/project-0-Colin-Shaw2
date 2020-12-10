package com.revature.repositories;

import java.util.HashMap;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public class CustomerFileDAO implements CustomerDAO{

	static HashMap<String, User> db = FakeFileDB.db;
	
	@Override
	public Customer addCustomer(Customer u) {
		db.put(u.getUsername(), u);
		return u;
	}
	
	@Override
	public Customer updateCustomer(Customer u) {
		db.put(u.getUsername(), u);
		return u;
	}
	
	@Override
	public Customer findCustomerByName(String s) {
		for(User u : db.values()) {
			if(s.equals(u.getUsername()) && u instanceof Customer) {
				return (Customer)u;
			}
		}
		return null;
	}

}
