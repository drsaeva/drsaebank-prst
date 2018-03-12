package com.revature.dsaebank_prst.dao;

import java.util.List;
import com.revature.dsaebank_prst.pojo.User;

public interface UserDao {
	
	public void createUser(User user);
	public List<User> retrieveAllCustomers();
	public User retrieveUser(String username);
	public void updateUser(String username);
	public void deleteUser(String username);
	
}
