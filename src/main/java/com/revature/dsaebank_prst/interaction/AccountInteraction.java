package com.revature.dsaebank_prst.interaction;

import com.revature.dsaebank_prst.pojo.Account;

public interface AccountInteraction {
	
	public void deposit(Account account, double amount);
	public void withdraw(Account account, double amount);
	public void transferTo(Account fromAccount, Account toAaccount, double amount);

}
