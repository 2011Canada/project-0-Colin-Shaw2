package com.revature.services;

import com.revature.models.User;

public interface UserServiceInterface {

	public User login(String username, String password);

	public void logout();

	public Boolean registerNewCustomerAccount(String username, String password);

	
}
