package com.revature.dsaebank_prst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dsaebank_prst.pojo.Application;
import com.revature.dsaebank_prst.util.ConnectionFactory;

public class ApplicationDaoImpl implements ApplicationDao {

	/**
	 * Creates a new Application record in the Applications table
	 * @param application - Application pojo containing the data corresponding to the intended new record in the DB
	 */
	public void createApplication(Application application) {
		Connection conn = ConnectionFactory.getInstance().getConnection();
		String sql = "";
		boolean joint = (application.getJointAccountNumber() != 0L);
		if (joint) sql = "INSERT INTO APPLICATIONS (USERNAME, STATUS, JOINT, JOINT_ACC_NUM) VALUES('"+application.getUsername() 
						+"', "+application.getApprovalStatus()+", "+application.getJoint()+","
						+application.getJointAccountNumber()+") ";
		else sql = "INSERT INTO APPLICATIONS (USERNAME, STATUS, JOINT) VALUES('"+application.getUsername() 
		+"', "+application.getApprovalStatus()+", "+application.getJoint()+") ";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			int rowsAffected = ps.executeUpdate();
			conn.commit();
			//TODO LoggingHandler method for application creation
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all existing Application records from the DB
	 * @param username String corresponding to the unique APPLICATION.USERNAME column in the Application record
	 * @return				Application pojo containing the data stored in the record
	 */
	public List<Application> retrieveAllOpenApplications() {
		List<Application> allApplications = new ArrayList<Application>();
		String sql = "SELECT * FROM APPLICATIONS ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);	
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				
				allApplications.add(new Application(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getLong(5)
						));
			}			
			//TODO LoggingHandler method for account retrieval
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allApplications;
	}

	/**
	 * Retrieves an existing Application record from the DB
	 * @param username String corresponding to the unique APPLICATION.USERNAME column in the Application record
	 * @return				Application pojo containing the data stored in the record
	 */
	public Application retrieveApplication(String username) {
		Application application = new Application();
		String sql = "SELECT * FROM APPLICATIONS WHERE ? ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);	
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) { 
				application.setId(rs.getInt(1));
				application.setUsername(rs.getString(2));
				application.setApprovalStatus(rs.getInt(3));
				application.setJoint(rs.getInt(4));
				application.setJointAccountNumber(rs.getLong(5));
			}			
			//TODO LoggingHandler method for account retrieval
			
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return application;
	}
	
	/**
	 * Updates an active Application record in the DB view Open_Applications
	 * @param application - Application pojo containing the decision status to update the existing record with
	 */
	public void applicationDecision(Application application) {
		String sql = "UPDATE OPEN_APPLICATIONS SET STATUS="+application.getApprovalStatus()
						+" WHERE USERNAME = ? ";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, application.getUsername());
			int rowsAffected = ps.executeUpdate();
			conn.commit();
			//TODO LoggingHandler method for appl update
			
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
