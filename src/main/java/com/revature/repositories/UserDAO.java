package com.revature.repositories;

import com.revature.models.Employee;
import com.revature.models.User;

public interface UserDAO {

	public User updateUser(User u);
	
	public Employee updateEmployee(Employee u);
	
	public User[] findAllUsers();
	
	public User findUserByName(String s);

	
}
