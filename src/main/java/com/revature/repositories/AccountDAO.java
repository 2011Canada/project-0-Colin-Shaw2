package com.revature.repositories;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountDAO {

	public Account addAccount(Customer u, Account a)throws SQLException;

	public Account updateAccountByCustomerandID(Customer customer, int id,  Account account) throws AccountNotFoundException, UserNotFoundException, SQLException;

	public List<Account> findAllAccountsFromCustomerName(String username) throws AccountNotFoundException, SQLException;
	
	public Account findAccountByCustomerandID(Customer c,int id) throws AccountNotFoundException, SQLException;

}
