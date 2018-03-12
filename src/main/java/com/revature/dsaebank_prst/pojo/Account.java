package com.revature.dsaebank_prst.pojo;

public class Account {
	
	private int id;
	private long accountNumber;
	private int ownerCount;
	private double balance;

	// no args constructor for normal use with DAO, set fields with getters/setters
	public Account() {	}
	
	// args constructor sans id, for init client-side of novel accounts and testing of that functionality
	public Account(long accountNumber, int ownerCount, double balance) {
		this.accountNumber = accountNumber;
		this.ownerCount = ownerCount;
		this.balance = balance;
	}
	
	// full args constructor, FOR TESTING ONLY
	public Account(int id, long accountNumber, int ownerCount, double balance) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.ownerCount = ownerCount;
		this.balance = balance;
	}
	
	/* Accessors/Mutators */
	public int getId() {														 return id;}
	public void setId(int id) {												  this.id = id;}
	public long getAccountNumber() {								  return accountNumber;}
	public void setAccountNumber(long accountNumber) {	this.accountNumber = accountNumber;}
	public int getOwnerCount() {										 return ownerCount;}
	public void setOwnerCount(int ownerCount) {				  this.ownerCount = ownerCount;}
	public double getBalance() {											return balance;}
	public void setBalance(double balance) {						this.balance = balance;}
}
