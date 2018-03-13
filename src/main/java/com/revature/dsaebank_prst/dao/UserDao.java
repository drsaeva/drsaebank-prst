package com.revature.dsaebank_prst.dao;

import java.util.List;

import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.pojo.User;

public interface UserDao {
	
	public void createCustomer(User user);
	public List<User> retrieveAllCustomers();
	public List<User> retrieveCustomersOnAccount(Account account);
	//public User retrieveUser(String username);
	public void updateUser(User user);
	public void deleteUser(String username);
	
}
