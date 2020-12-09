package com.revature.repositories;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface AccountDAO {

	public Customer addAccount(Customer u, Account a);

	public Customer updateAccount(Customer u, Account a);

	public Account[] findAllAccounts();
	
	public Account findAccountByID(int id);

}
