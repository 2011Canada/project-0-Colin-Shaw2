package com.revature.repositories;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;

public interface UserDAO {

	public Customer addCustomer(Customer u);

	public User updateUser(User u);

	public Customer updateCustomer(Customer u);
	
	public Employee updateEmployee(Employee u);
	
	public User[] findAllUsers();
	
	public User findUserByName(String s);

	public Customer findCustomerByName(String username);
	
}
