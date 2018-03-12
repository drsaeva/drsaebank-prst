package com.revature.dsaebank_prst.dao;

import java.util.List;

import com.revature.dsaebank_prst.pojo.Account;

public interface AccountDao {
	
	public void createAccount(Account account);
	public List<Account> retrieveAllAccounts();
	public Account retrieveAccount(Long accountNumber);
	public void updateUser(Long accountNumber);
	public void deleteUser(Long accountNumber);
	
}
