package com.revature.dsaebank_prst.dao;

public class DaoFactory {
	// Dao instances in the factory
	private static UserDaoImpl bankUserDao = new UserDaoImpl();
	private static AccountDaoImpl bankAcctDao = new AccountDaoImpl();
	private static ApplicationDaoImpl bankApplDao = new ApplicationDaoImpl();
	
	// Default Constructor is OK for now
	
	// Accessors
	public static UserDaoImpl getBankUserDao() {		return bankUserDao;}
	public static AccountDaoImpl getBankAcctDao() {	return bankAcctDao;}
	public static ApplicationDaoImpl getBankApplDao() {return bankApplDao;}
	
}
