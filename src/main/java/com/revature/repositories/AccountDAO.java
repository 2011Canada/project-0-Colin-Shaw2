package com.revature.repositories;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountDAO {

	public Customer addAccount(Customer u, Account a);

	public Account updateAccountByCustomerandID(Customer c, int id,  Account a);

	public Account[] findAllAccounts() throws AccountNotFoundException;
	
	public Account[] findAllAccountsFromCustomerName(String username) throws AccountNotFoundException;
	
	public Account findAccountByCustomerandID(Customer c,int id) throws AccountNotFoundException;

}
