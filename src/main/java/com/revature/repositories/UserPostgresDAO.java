package com.revature.repositories;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserPostgresDAO implements UserDAO {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	//TODO add throws
	public User findUserByName(String s) {
		User u = null;
		Connection conn = this.cf.getConnection();

		String sql = "select * from customers where username like \'" + s + "\';";
		try {
			Statement statement = conn.createStatement();

			ResultSet res = statement.executeQuery(sql);
			res.next();
			u = new Customer(res.getString("username"), res.getString("password"));


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(sql);
		}

		if(u != null) {return u;}
		
		sql = "select * from employees where username like \'" + s + "\';";

		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);
			res.next();
			
			u = new Employee(res.getString("username"), res.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql);
		}


		return u;
	}

}
