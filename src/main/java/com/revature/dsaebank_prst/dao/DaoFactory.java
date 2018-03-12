package com.revature.dsaebank_prst.dao;

public class DaoFactory {
	// Dao instances in the factory
	private UserDaoImpl bankUserDao = new UserDaoImpl();
	private AccountDaoImpl bankAcctDao = new AccountDaoImpl();
	private ApplicationDaoImpl bankApplDao = new ApplicationDaoImpl();
	
	// Default Constructor is OK for now
	
	// Accessors
	public UserDaoImpl getBankUserDao() {		return bankUserDao;}
	public AccountDaoImpl getBankAcctDao() {	return bankAcctDao;}
	public ApplicationDaoImpl getBankApplDao() {return bankApplDao;}
	
}
