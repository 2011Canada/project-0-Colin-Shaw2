package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeePostgresDAO implements EmployeeDAO {
	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	public Employee addEmployee(Employee u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployee(Employee u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployeeByName(String username) throws UserNotFoundException {
		Connection conn = cf.getConnection();
		String sql = "select * from employees where username like ? ;";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			res.next();

			return new Employee(res.getString("username"), res.getString("password"));
		} catch (SQLException e) {
			throw new UserNotFoundException();
		}
	}

}
