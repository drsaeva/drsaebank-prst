package com.revature.dsaebank_prst.pojo;

public class User {
	private int id;
	private String username;
	private String password;
	private int accessLevel;
	
	// no args constructor, for normal use
	public User() {		}
	
	// args constructor sans id, for clientside init of novel users (customers) and testing of that functionality
	public User(String username, String password, int accessLevel) {
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;	
	}
	
	// full args constructor, FOR TEST PURPOSES ONLY
	public User(int id, String username, String password, int accessLevel) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;	
	}
	
	/* Accessors/Mutators */
	public int getId() {											  return id;}
	public void setId(int id) {									   this.id = id;}
	public String getUsername() {								return username;}
	public void setUsername(String username) {		   this.username = username;}
	public String getPassword() {								return password;}
	public void setPassword(String password) {		   this.password = password;}
	public int getAccessLevel() {							 return accessLevel;}
	public void setAccessLevel(int accessLevel) {this.accessLevel = accessLevel;}
}
