package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.User;

public interface UserDAO {

	public User updateUser(User u);
	
	public Employee updateEmployee(Employee u);
	
	public List<User> findAllUsers();
	
	public User findUserByName(String s);

	
}
