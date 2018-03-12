package com.revature.dsaebank_prst.pojo;

public class Application {
	private int id;
	private String username;
	private int approvalStatus, joint;
	private long jointAccountNumber;
	
	// no args constructor, for most use cases
	public Application() {		}
	
	// args constructor sans id, for clientside init of novel applications and testing of that functionality
	public Application(String username, int approvalStatus, int joint, long jointAccountNumber) {
		this.username = username;
		this.approvalStatus = approvalStatus;
		this.joint = joint;
		this.jointAccountNumber = jointAccountNumber;
	}
	
	// full args constructor, FOR TEST PURPOSES ONLY
		public Application(int id, String username, int approvalStatus, int joint, long jointAccountNumber) {
			this.id = id;
			this.username = username;
			this.approvalStatus = approvalStatus;
			this.joint = joint;
			this.jointAccountNumber = jointAccountNumber;
		}
	
	/*	Accessors/Mutators	*/
	public int getId() {																		  return id;}
	public void setId(int id) {																   this.id = id;} 
	public String getUsername() {						 									return username;}
	public void setUsername(String username) {									   this.username = username;}
	public int getApprovalStatus() {			   								      return approvalStatus;}
	public void setApprovalStatus(int approvalStatus) { 		       this.approvalStatus = approvalStatus;}
	public int getJoint() {																	   return joint;}
	public void setJoint(int joint) {													 this.joint = joint;}
	public long getJointAccountNumber() {										  return jointAccountNumber;}
	public void setJointAccountNumber(long jointAccountNumber){this.jointAccountNumber = jointAccountNumber;}
}
