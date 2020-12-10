package com.revature.repositories;

import com.revature.models.Employee;

public interface EmployeeDAO {

	public Employee addEmployee(Employee u);

	public Employee updateEmployee(Employee u);
		
	public Employee findEmployeeByName(String username);
	
}
