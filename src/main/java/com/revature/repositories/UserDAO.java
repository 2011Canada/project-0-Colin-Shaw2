package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Employee;
import com.revature.models.User;

public interface UserDAO {

//	public User updateUser(User u) throws UserNotFoundException;
//	
//	public Employee updateEmployee(Employee u)throws UserNotFoundException;
//	
//	public List<User> findAllUsers()throws UserNotFoundException;
//	
	public User findUserByName(String s)throws UserNotFoundException;

	
}
