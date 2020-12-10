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
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployee(Employee u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {
//		Connection conn = this.cf.getConnection();
//		
//		String sql = "select * from customers;";
//		
//		try {
//			Statement s = conn.createStatement();
//			
//			ResultSet res = s.executeQuery(sql);
//			List<User> all = new ArrayList<>();
//			
//			while(res.next()){
//				Customer c = new Customer(res.getInt(""), 
//						String username, 
//						String password, 
//						ArrayList<Account> accounts);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public User findUserByName(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
