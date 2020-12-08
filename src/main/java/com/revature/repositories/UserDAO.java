package com.revature.repositories;

import com.revature.models.Customer;
import com.revature.models.User;

public interface UserDAO {

	public Customer addCustomer(Customer u);

	public User updateUser(User u);
	
	public User[] findAllUsers();
	
	public User findUserByName(String s);
	
	//TODO copy alec
	
	//findAll
	//findUserByName
	

}
