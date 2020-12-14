package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountDAO {

	public Customer addAccount(Customer u, Account a);

	public Account updateAccountByCustomerandID(Customer customer, int id,  Account account) throws AccountNotFoundException, UserNotFoundException;

	public List<Account> findAllAccounts() throws AccountNotFoundException;
	
	public List<Account> findAllAccountsFromCustomerName(String username) throws AccountNotFoundException;
	
	public Account findAccountByCustomerandID(Customer c,int id) throws AccountNotFoundException;

}
