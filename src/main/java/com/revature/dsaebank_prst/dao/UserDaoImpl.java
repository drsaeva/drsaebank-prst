package com.revature.dsaebank_prst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.pojo.User;
import com.revature.dsaebank_prst.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {
	/**
	 * Creates a new User record in the User table
	 * @param user - User pojo containing the data corresponding to the intended new record in the DB
	 */
	public void createCustomer(User user) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "INSERT INTO USERS (USERNAME, USERPW, FULLNAME, ACCESS_LEVEL) VALUES('"+user.getUsername() 
						+"', '"+user.getPassword()+"', '"+user.getName()+"', -1 ) ";
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
	 * Returns all Customer-permissioned (ACCESS_LEVEL=-1) records in the Users table
	 * @return 		a List of Account pojos corresponding to every Account record with Customer-permissions in the DB
	 */
	public List<User> retrieveAllCustomers() {
		List<User> customers = new ArrayList<User>();
		String sql = "SELECT * FROM CUSTOMERS ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);	
			ResultSet rs = ps.executeQuery();
			int i=0;
			while (rs.next()) {
				// initialize next row as another Customer in the List
				customers.add(new User());
				customers.get(i).setId(rs.getInt(1));
				customers.get(i).setUsername(rs.getString(2));
				i++;
			}			
			//TODO LoggingHandler method for account retrieval
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return customers;
	}
	
	/**
	 * Returns all Customer-permissioned Users with ownership rights on a specified Account
	 * @param account   the Account record in question
	 * @return 			a List of User pojos corresponding to every Customer record with access to the Account
	 */
	public List<User> retrieveCustomersOnAccount(Account account) {
		List<User> customers = new ArrayList<User>();
		String sql = "SELECT C.ID, C.USERNAME, C.FULLNAME from CUSTOMERS C JOIN "
					+ "( CUSTOMERS_ACCOUNTS CA JOIN ACCOUNTS A ON CA.ACCOUNT_ID=A.ID) "
					+ "ON C.ID=CA.CUSTOMERID WHERE A.NUM=? ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setLong(1, account.getAccountNumber());
			ResultSet rs = ps.executeQuery();
			int i=0;
			while (rs.next()) {
				// initialize next row as another Customer in the List
				customers.add(new User());
				customers.get(i).setId(rs.getInt(1));
				customers.get(i).setUsername(rs.getString(2));
				i++;
			}			
			//TODO LoggingHandler method for account retrieval
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	/**
	 * Returns User record from table corresponding to a given username
	 * @param username	the unique username on the record, if it exists
	 * @return 			the User record in which the username is stored
	 */
	public User retrieveUser(String username) {
		User user = null;
		String sql = "SELECT * FROM USERS WHERE USERNAME=? ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			user = new User();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setAccessLevel(rs.getInt(5));
			}			
			//TODO LoggingHandler method for account retrieval
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * Updates an User record in the DB table Users
	 * @param user - User pojo containing fieldstates differing from the existing DB record
	 */
	public void updateUser(User user) {
		String sql = "UPDATE USERS SET USERNAME='"+user.getUsername()+"', USERPW='"+user.getPassword()
					+"', FULLNAME='"+user.getName()+"' WHERE ID= ? ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,user.getId());
			int rowsAffected = ps.executeUpdate(); //array of results representing the row found
			conn.commit();
			//TODO LoggingHandler method for account update
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Deletes a User record in the DB table Users
	 * @param username - String referencing the same unique element in the corresponding record in Users
	 */	
	public void deleteUser(String username) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "DELETE FROM USERS WHERE USERNAME="+username+" ";
		
		try {
			conn.setAutoCommit(false);
			
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
