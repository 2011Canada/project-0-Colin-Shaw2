package com.revature.services;

import com.revature.models.User;

public interface UserServiceInterface {

	public User login(String username, String password);

	public User registerNewAcount(String username, String password, long acountBalance);
	
}
