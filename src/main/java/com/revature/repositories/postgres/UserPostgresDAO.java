package com.revature.repositories.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.UserDAO;
import com.revature.util.ConnectionFactory;

public class UserPostgresDAO implements UserDAO {

	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	private static CustomerDAO cd = new CustomerPostgresDAO();
	private static EmployeeDAO ed = new EmployeePostgresDAO();

	@Override
	public User findUserByName(String s, Boolean isEmployee) throws UserNotFoundException, SQLException, AccountNotFoundException{
		User u = cd.findCustomerByName(s);
		
		if(u != null) {
			return u;
			}
		u = ed.findEmployeeByName(s);
		
		
		if(u != null) {
			return u;
			}

		throw new UserNotFoundException();
	}

}
