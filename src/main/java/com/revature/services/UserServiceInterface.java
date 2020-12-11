package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserServiceInterface {

	public User login(String username, String password) throws UserNotFoundException;

	public void logout();

	public Boolean registerNewCustomerAccount(String username, String password);

	
}
