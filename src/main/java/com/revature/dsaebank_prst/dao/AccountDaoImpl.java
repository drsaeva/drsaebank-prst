package com.revature.dsaebank_prst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao  {
	
	/**
	 * Creates a new Account record in the Accounts table
	 * @param account - Account pojo containing the data corresponding to the intended new record in the DB
	 */
	public void createAccount(Account account) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO ACCOUNTS (NUM, OWNER_COUNT, BALANCE) VALUES('"+account.getAccountNumber() 
						+"', '"+account.getOwnerCount()+"', '"+account.getBalance()+"') ";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int rowsAffected = ps.executeUpdate(sql);
			conn.commit();
			//TODO LoggingHandler method for account creation
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns all Account records in the Accounts table
	 * @return 		a List of Account pojos corresponding to every Account record in the DB
	 */
	public List<Account> retrieveAllAccounts() {
		List<Account> results = new ArrayList<Account>();
		String sql = "SELECT * FROM ACCOUNTS ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);	
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				// initialize next row as another Account in the List
				results.add(new Account(
						rs.getInt(1),
						rs.getLong(2),
						rs.getInt(3),
						rs.getDouble(4)
						));
			}			
			//TODO LoggingHandler method for account retrieval
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return results;
	}
	
	/**
	 * Retrieves an existing Account record from the DB
	 * @param accountNumber 10-digit Long corresponding to the unique ACCOUNT.NUM column in the Account record
	 * @return				Account pojo containing the data stored in the record
	 */
	public Account retrieveAccount(Long accountNumber) {
		Account account = new Account();
		String sql = "SELECT * FROM ACCOUNTS WHERE ? ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(2, accountNumber);	
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) { 
				account.setId(rs.getInt(1));
				account.setAccountNumber(rs.getLong(2));
				account.setOwnerCount(rs.getInt(3));
				account.setBalance(rs.getDouble(4));
			}			
			//TODO LoggingHandler method for account retrieval
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}
	
	/**
	 * Updates an Account record in the DB table Accounts
	 * @param account - Account pojo containing fieldstates differing from the existing DB record in Accounts
	 */
	public void updateAccount(Account account) {
		String sql = "UPDATE ACCOUNTS SET OWNER_COUNT="+account.getOwnerCount()
						+", BALANCE="+account.getBalance()+" WHERE NUM = ? ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(2, account.getAccountNumber());
			int rowsAffected = ps.executeUpdate(); //array of results representing the row found
			conn.commit();
			//TODO LoggingHandler method for account update
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes an Account record in the DB table Accounts
	 * @param accountNumber - 10-digit long referencing the same unique element in the corresponding record in Accounts
	 */
	public void deleteAccount(Long accountNumber) {
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM ACCOUNTS WHERE NUM="+accountNumber+") ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int rowsAffected = ps.executeUpdate();
			conn.commit();
			//TODO LoggingHandler method for account deletion
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
