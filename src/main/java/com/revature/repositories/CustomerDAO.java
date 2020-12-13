package com.revature.repositories;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public interface CustomerDAO{

	public Customer addCustomer(Customer u);

	public Customer updateCustomer(Customer u);
		
	public Customer findCustomerByName(String username) throws UserNotFoundException;
	
	
}
