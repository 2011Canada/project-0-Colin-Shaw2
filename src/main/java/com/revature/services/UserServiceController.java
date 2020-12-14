package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.UserFileDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerFileDAO;
import com.revature.repositories.UserDAO;


public class UserServiceController implements UserServiceInterface {

	private static UserDAO userDAO = new UserFileDAO();
	private static CustomerDAO customerDAO = new CustomerFileDAO();
	private static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");
	
	public UserServiceController() {}

	//can be null
	@Override
	public User login(String username, String password) throws UserNotFoundException{
		eventLogger.info("login "  + username);
		User u = userDAO.findUserByName(username);
		if(u.getPassword().equals(password)) {
			return u;
		}
		throw new UserNotFoundException();
	}
	
	@Override
	public Customer registerNewCustomerAccount(String username, String password) {
		eventLogger.info("registerNewCustomerAccount "  + username + " " + password);
		return customerDAO.addCustomer(new Customer(username, password));
	}

	@Override
	public void logout(String username) {
		eventLogger.info("logout"  + username);
	}

}
