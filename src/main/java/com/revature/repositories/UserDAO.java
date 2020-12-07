package com.revature.repositories;

import com.revature.models.User;

public interface UserDAO {
	
	public User saveOne(User u);

	public User updateUser(User u);
	
	public User[] findAllUsers();
	
	public User findUserByName(String s);
	
	//TODO copy alec
	
	//findAll
	//findUserByName
	

}
