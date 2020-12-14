package com.revature.repositories.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Employee;
import com.revature.repositories.EmployeeDAO;
import com.revature.util.ConnectionFactory;

public class EmployeePostgresDAO implements EmployeeDAO {
	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	public Employee addEmployee(Employee employee) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "insert into employees values (?, ?);";
		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, employee.getUsername());
		statement.setString(2, employee.getPassword());
		statement.executeUpdate();

		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) throws UserNotFoundException, SQLException  {
		Connection conn = cf.getConnection();

		String sql = "update employees set \"username\" = ?, \"password\" = ? where username like ?;";
		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, employee.getUsername());
		statement.setString(2, employee.getPassword());
		statement.setString(3, employee.getUsername());
		if(statement.executeUpdate()==0) {
			throw new UserNotFoundException();
		};

		return employee;
	}

	@Override
	public Employee findEmployeeByName(String username) throws UserNotFoundException, SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from employees where username like ? ;";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet res = statement.executeQuery();
		res.next();

		return new Employee(res.getString("username"), res.getString("password"));

	}

}
