package com.revature.dsaebank_prst.pojo;

public class Application {
	
	private String username;
	private int approvalStatus, joint;
	private long jointAccountNumber;
	
	public Application(String username, int approvalStatus, int joint, long jointAccountNumber) {
		this.username = username;
		this.approvalStatus = approvalStatus;
		this.joint = joint;
		this.jointAccountNumber = jointAccountNumber;
	}
	
	/*
	 * Accessors/Mutators
	 */
	public String getUsername() {						 									return username;}
	public void setUsername(String username) {									   this.username = username;}
	public int getApprovalStatus() {			   								      return approvalStatus;}
	public void setApprovalStatus(int approvalStatus) { 		       this.approvalStatus = approvalStatus;}
	public int getJoint() {																	   return joint;}
	public void setJoint(int joint) {													 this.joint = joint;}
	public long getJointAccountNumber() {										  return jointAccountNumber;}
	public void setJointAccountNumber(long jointAccountNumber){this.jointAccountNumber = jointAccountNumber;}
}
