package com.revature.services;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.UserDAO;
import com.revature.repositories.file.CustomerFileDAO;
import com.revature.repositories.file.EmployeeFileDAO;
import com.revature.repositories.file.UserFileDAO;


public class UserServiceController implements UserServiceInterface {

	private static UserDAO userDAO = new UserFileDAO();
	private static CustomerDAO customerDAO = new CustomerFileDAO();
	private static EmployeeDAO employeeDAO = new EmployeeFileDAO();
	private static Logger eventLogger = LogManager.getLogger("com.revature.project0ColinEventLogger");
	
	public UserServiceController() {}

	//can be null
	@Override
	public User login(String username, String password, Boolean isEmployee) throws UserNotFoundException, SQLException, AccountNotFoundException{
		eventLogger.info("login "  + username);
		User u =
		(isEmployee)?
		customerDAO.findCustomerByName(username):
		employeeDAO.findEmployeeByName(username);
		if(u.getPassword().equals(password)) {
			return u;
		}
		throw new UserNotFoundException();
	}
	
	@Override
	public Customer registerNewCustomerAccount(String username, String password) throws AccountNotFoundException, TransferNotFoundException, UserNotFoundException, SQLException {
		eventLogger.info("registerNewCustomerAccount "  + username + " " + password);
		return customerDAO.addCustomer(new Customer(username, password));
	}

	@Override
	public void logout(String username) {
		eventLogger.info("logout"  + username);
	}

}
