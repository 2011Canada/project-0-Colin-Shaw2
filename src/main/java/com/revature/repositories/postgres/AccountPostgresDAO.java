package com.revature.repositories.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.AccountState;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.AccountDAO;
import com.revature.util.ConnectionFactory;

public class AccountPostgresDAO implements AccountDAO {

	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	public Account addAccount(Customer customer, Account a) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "insert into accounts values(?, ?, ?) returning account_id;";
		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, customer.getUsername());
		statement.setInt(2, (int) a.getBalance());
		statement.setString(3, a.getAccountState().toString());
		ResultSet rs = statement.executeQuery();
		rs.next();
		a.setAccountId(rs.getInt("account_id"));
		return a;
	}

	@Override
	public Account updateAccountByCustomerandID(Customer customer, int id, Account a)
			throws AccountNotFoundException, SQLException {
		Connection conn = cf.getConnection();

		String sql = "update accounts set balance = ?, account_state=? where account_id = ?;";
		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setLong(1, a.getBalance());
		statement.setString(2, a.getAccountState().toString());
		statement.setInt(3, id);
		if (statement.executeUpdate() == 0) {
			throw new AccountNotFoundException();
		}
		;
		a.setAccountId(id);
		return a;
	}

	@Override
	public List<Account> findAllAccountsFromCustomerName(String username) throws AccountNotFoundException, SQLException {
		Connection conn = cf.getConnection();
		ArrayList<Account> accs = new ArrayList<>();
		String sql = "select * from accounts where accountuser like ?;";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			AccountState state = AccountState.valueOf(res.getString("account_state"));
			accs.add(
					new Account(res.getLong("balance"), res.getInt("account_id"), res.getString("accountuser"), state));
		}

		return accs;
	}
	
	public List<Account> findAllPendingAccountsFromCustomerName(String username) throws AccountNotFoundException, SQLException{
		Connection conn = cf.getConnection();
		ArrayList<Account> accs = new ArrayList<>();
		String sql = "select * from accounts where accountuser like ?  and account_state='PENDING';";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			AccountState state = AccountState.valueOf(res.getString("account_state"));
			accs.add(
					new Account(res.getLong("balance"), res.getInt("account_id"), res.getString("accountuser"), state));
		}

		return accs;
	}
	

	@Override
	public Account findAccountByCustomerandID(Customer c, int id) throws AccountNotFoundException, SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from accounts where accountuser like ? and account_id = ?;";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, c.getUsername());
		statement.setInt(2, id);
		ResultSet res = statement.executeQuery();
		res.next();

		AccountState state = AccountState.valueOf(res.getString("account_state"));
		return new Account(res.getLong("balance"), res.getInt("account_id"), res.getString("accountuser"), state);
	}

}
