package com.revature.services;

import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.UserFileDAO;
import com.revature.repositories.UserDAO;

public class UserServiceController implements UserServiceInterface {
	
	protected static UserDAO dao = new UserFileDAO();

	public UserServiceController() {
		// TODO Auto-generated constructor stub
	}

	//can be null
	@Override
	public User login(String username, String password) {
		User u = dao.findUserByName(username);
		return u;
	}
	
	@Override
	public Boolean registerNewCustomerAccount(String username, String password) {
		return (dao.addCustomer(new Customer(username, password)))== null?false:true;
	}

	@Override
	public void logout() {
		//TODO
		//does logging
	}

}
