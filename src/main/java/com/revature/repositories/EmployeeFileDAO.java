package com.revature.repositories;

import java.util.HashMap;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;


public class EmployeeFileDAO implements EmployeeDAO {

	static HashMap<String, User> db = FakeFileDB.db;

	@Override
	public Employee addEmployee(Employee u) {
		db.put(u.getUsername(), u);
		return u;
	}

	@Override
	public Employee updateEmployee(Employee u) {
		db.put(u.getUsername(), u);
		return u;
	}

	@Override
	public Employee findEmployeeByName(String username) {
		for(User u : db.values()) {
			if(username.equals(u.getUsername()) && u instanceof Customer) {
				return (Employee)u;
			}
		}
		return null;
	}

}
