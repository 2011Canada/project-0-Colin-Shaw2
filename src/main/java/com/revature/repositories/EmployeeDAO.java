package com.revature.repositories;

import java.sql.SQLException;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Employee;

public interface EmployeeDAO {

	public Employee addEmployee(Employee u) throws SQLException;

	public Employee updateEmployee(Employee u) throws SQLException, UserNotFoundException;
		
	public Employee findEmployeeByName(String username) throws UserNotFoundException, SQLException;
	
}
