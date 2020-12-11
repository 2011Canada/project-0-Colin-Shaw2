package com.revature.services;

import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.UserFileDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerFileDAO;
import com.revature.repositories.UserDAO;


public class UserServiceController implements UserServiceInterface {

	protected static UserDAO userDAO = new UserFileDAO();
	protected static CustomerDAO customerDAO = new CustomerFileDAO();

	public UserServiceController() {}

	//can be null
	@Override
	public User login(String username, String password) {
		User u = userDAO.findUserByName(username);
		if(u  == null) {
			return null;
		}
		if(u.getPassword().equals(password)) {
			return u;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Boolean registerNewCustomerAccount(String username, String password) {
		return (customerDAO.addCustomer(new Customer(username, password)))== null?false:true;
	}

	@Override
	public void logout() {
		//TODO
		//does logging
	}

}
