package com.revature.services;

import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.FileDAO;
import com.revature.repositories.UserDAO;

public class UserServiceController implements UserServiceInterface {
	
	static UserDAO dao = new FileDAO();

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
		return dao.addCustomer(new Customer(username, password));
	}

	@Override
	public void logout() {
		//TODO
		//does logging
	}

}
