package com.revature.dsaebank_prst.util;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.pojo.User;


public class LoggingHandler {
	private static Logger log = Logger.getRootLogger();
	private static final NumberFormat usdAmount = NumberFormat.getCurrencyInstance(Locale.getDefault()); 
	
	public static void logDeposit(User user, Account acct, double amount) {
		log.info(user.getUsername() +" deposited "+usdAmount.format(amount)+" into account number "+acct.getAccountNumber());
	}	
	//TODO refactor to remove User passed in and instead put 
	public static void logWithdrawal(User user, Account acct, double amount) {
		log.info(user.getUsername() +" withdrew "+usdAmount.format(amount)+" from account number "+acct.getAccountNumber());
	}	
	
}
