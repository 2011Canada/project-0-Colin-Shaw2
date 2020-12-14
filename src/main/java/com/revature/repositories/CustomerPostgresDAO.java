package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.ConnectionFactory;

public class CustomerPostgresDAO implements CustomerDAO {

	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	private static AccountDAO aDAO = new AccountPostgresDAO();
	
	@Override
	public Customer addCustomer(Customer c) throws AccountNotFoundException, TransferNotFoundException, UserNotFoundException {
		Connection conn = cf.getConnection();
		
		
		String sql = "insert into customers values\r\n" + "(?, ?);";
		try {
			conn.setAutoCommit(false);
			
			//this sets the customer data
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, c.getUsername());
			statement.setString(2, c.getPassword());
			statement.executeUpdate();
			
			//this sets the account data
			for(Account a : c.getAccounts()) {
				aDAO.updateAccountByCustomerandID(c, a.getAccountID(), a);
			}
			
			//this sets the transaction data
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// System.out.println(sql);
		}
		return c;
	}

	@Override
	public Customer updateCustomer(Customer c) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByName(String username) throws UserNotFoundException, AccountNotFoundException {
		Connection conn = cf.getConnection();
		Customer u = null;
		String sql = "select * from customers where username like ?;";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			res.next();
			u = new Customer(res.getString("username"), res.getString("password"));
			for (Account a :aDAO.findAllAccountsFromCustomerName(username)){
				u.addAccount(a);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			//System.out.println(sql);
		}
		return u;
	}

}
