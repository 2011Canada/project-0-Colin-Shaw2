package com.revature.repositories.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.CustomerDAO;
import com.revature.util.ConnectionFactory;

public class CustomerPostgresDAO implements CustomerDAO {

	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	private static AccountDAO aDAO = new AccountPostgresDAO();

	@Override
	public Customer addCustomer(Customer c)
			throws AccountNotFoundException, TransferNotFoundException, UserNotFoundException, SQLException {
		Connection conn = cf.getConnection();

		String sql = "insert into customers values\r\n" + "(?, ?);";
		conn.setAutoCommit(false);

		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, c.getUsername());
		statement.setString(2, c.getPassword());
		statement.executeUpdate();

		// this sets the account data
		for (Account a : c.getAccounts()) {
			aDAO.updateAccountByCustomerandID(c, a.getAccountID(), a);
		}

		// this sets the transaction data
		conn.commit();
		return c;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws UserNotFoundException, SQLException {
		Connection conn = cf.getConnection();

		String sql = "update customers set \"username\" = ?, \"password\" = ? where username like ?;";
		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, customer.getUsername());
		statement.setString(2, customer.getPassword());
		statement.setString(3, customer.getUsername());
		if(statement.executeUpdate()==0) {
			throw new UserNotFoundException();
		};

		return customer;
	}

	@Override
	public Customer findCustomerByName(String username)
			throws UserNotFoundException, AccountNotFoundException, SQLException {
		Connection conn = cf.getConnection();
		Customer u = null;
		String sql = "select * from customers where username like ?;";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet res = statement.executeQuery();
		res.next();
		u = new Customer(res.getString("username"), res.getString("password"));
		
		
		for (Account a : aDAO.findAllAccountsFromCustomerName(username)) {
			u.addAccount(a);
		}
		return u;
	}

}
