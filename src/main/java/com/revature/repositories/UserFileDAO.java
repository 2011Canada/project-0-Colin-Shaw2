package com.revature.repositories;


import java.util.HashMap;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public class UserFileDAO implements UserDAO {
	

	static HashMap<String, User> db = FakeFileDB.db;

	public UserFileDAO() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Employee updateEmployee(Employee u) {
		// TODO Auto-generated method stub
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
		for(User u : db.values()) {
			if(s.equals(u.getUsername())) {
				return u;
			}
		}
		return null;
	}

	


}
