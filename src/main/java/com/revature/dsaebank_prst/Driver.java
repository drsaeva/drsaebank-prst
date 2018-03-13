package com.revature.dsaebank_prst;

import java.util.Scanner;

import com.revature.dsaebank_prst.dao.DaoFactory;
import com.revature.dsaebank_prst.pojo.User;
import com.revature.dsaebank_prst.util.LoginSession;
import com.revature.dsaebank_prst.util.UserInputHandler;


public class Driver {
	
	public static final Scanner s = new Scanner(System.in);
	
    public static void main( String[] args ) {
        System.out.println("BankC - A New Way to Bank Electronically. Copyright 2018.");
        System.out.println("Welcome to Ekaf National Bank and Trust\n Please Select a number "
        		+ "from one of the menu options below:\n\n"
        		+ "1 - Login as a current bank member\n"
        		+ "2 - Register as a new member\n"
        		+ "3 - Exit \n\nPlease enter your option below:");
        	
        int choice = UserInputHandler.getInstance().getNewInt(1, 3);
        switch(choice) {
        	
        	case 1: LoginSession.getInstance().logIn();
        			break;
        	
        	//create new customer 
        	case 2: DaoFactory.getBankUserDao().createCustomer(getNewCustomerInfo());
        			break;
        	case 3: System.out.println("Thank you!");
        			break;
        	default: break;
        }
    
    }
    
    /**
     * Prompts a new customer for a username, password, and their name. Intializes and returns a new User pojo to  
     * store the information so it can be passed to the UserDao to create a new Users record in the DB
     * @return 		new User pojo containing the new Customer's information
     */
    public static User getNewCustomerInfo() {
    	System.out.println("Thank you for your interest in membership at Ekaf National Bank and Trust!\n"
    			+ "Please provide the required personal information for a new user profile when prompted.\n"
    			+ "First, please provide a username to log in with:\n");
    	String username = UserInputHandler.getInstance().getNewString();
    	System.out.println("Thanks!\nNext, please enter a new password below to secure your user account:\n");
    	String userpw = UserInputHandler.getInstance().getNewString();
    	System.out.println("Thanks!\nFinally, please provide us a name so a bank employee will be able to\n"
    			+ "more easily reference your account and reach out to you:\n");
    	String name = UserInputHandler.getInstance().getNewString();
    	System.out.println("\nGreat! Your new user profile will be created shortly.\n"
    			+ "This program will now exit - please login to your new profile to\n"
    			+ "apply for a new account or joint account access.");
     	return new User(username,userpw,name,-1);
    }
    
    
}
