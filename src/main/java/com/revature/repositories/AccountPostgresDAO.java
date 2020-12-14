package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.AccountState;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.ConnectionFactory;

public class AccountPostgresDAO implements AccountDAO {

	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	@Override
	public Customer addAccount(Customer u, Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccountByCustomerandID(Customer c, int id, Account a) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAllAccounts() throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAllAccountsFromCustomerName(String username) throws AccountNotFoundException {
		Connection conn = cf.getConnection();
		ArrayList<Account> accs = new ArrayList<>();
		String sql = "select * from accounts where accountuser like ?;";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			
			while(res.next()){
				AccountState state = AccountState.valueOf( res.getString("account_state"));
				accs.add(new Account(res.getLong("balance"), res.getInt("account_id"), res.getString("accountuser"), state));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			//System.out.println(sql);
		}
		return accs;
	}

	@Override
	public Account findAccountByCustomerandID(Customer c, int id) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
