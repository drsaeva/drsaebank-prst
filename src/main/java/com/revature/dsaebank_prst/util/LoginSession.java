package com.revature.dsaebank_prst.util;

import java.util.List;

import com.revature.dsaebank_prst.dao.DaoFactory;
import com.revature.dsaebank_prst.interaction.ApplicationInteractionImpl;
import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.pojo.Application;
import com.revature.dsaebank_prst.pojo.User;

public class LoginSession {
	
	private static LoginSession ls = null;
	private User loggedIn = null;
	
	private LoginSession() {
		loggedIn = logIn();
		
	}
	
	//synch to prevent issues stemming from race conditions
	public static synchronized LoginSession getInstance() {
		// ensure that ls can be initialized only if it is null, otherwise it is permanently set
		if (ls == null) ls = new LoginSession();
		return ls;
	}	
	
	public static User logIn() {
		User loggedIn = null;
		System.out.println("Please provide a valid username to log in with, or enter 'cancel' to exit:");
		String username = UserInputHandler.getInstance().getNewString();
		if (username.toLowerCase().trim().equals("cancel")) return null;
		else {
			loggedIn=DaoFactory.getBankUserDao().retrieveUser(username);
			System.out.println("Please provide your password:");
			String userpwInput = UserInputHandler.getInstance().getNewString();
			if (userpwInput.toString().equals(loggedIn.getPassword())) {
				System.out.println("\nWelcome, "+loggedIn.getName()+". Your menu options are as follows:\n");
				System.out.println(getPermissionedLoginMenu(loggedIn));
				int loginMenuChoice = UserInputHandler.getInstance().getNewInt(1, 3);
				while (loginMenuChoice != 3) {
					if (loginMenuChoice == 2) {
						ApplicationInteractionImpl applying = new ApplicationInteractionImpl();
						if (loggedIn.getAccessLevel() == -1) {
							Application newApp = applying.applicationProcess(loggedIn);
							DaoFactory.getBankApplDao().createApplication(newApp);
						} else {
							List<Application> allAppls = DaoFactory.getBankApplDao().retrieveAllOpenApplications();
							System.out.println("Number\tUsername\t Single(0)/Joint(1)\tJointAccountNumber\n"
									+ "----------------------------------------------------------------");
							for(Application a: allAppls) if (a.getApprovalStatus()==0)
								System.out.println(allAppls.indexOf(a)+"\t"+a.getUsername()+"\t"+a.getJoint()+"\t\t"+a.getJointAccountNumber());
							System.out.println("Please choose an application to make a decision on:");
							int choice = UserInputHandler.getInstance().getNewInt(0, allAppls.size()-1);
							allAppls.get(choice).setApprovalStatus(applying.makeDecision(allAppls.get(choice), loggedIn.getAccessLevel()));
							Account updated = applying.updateAccount(allAppls.get(choice), loggedIn);
							DaoFactory.getBankAcctDao().createAccountAndJoin(updated, loggedIn, allAppls.get(choice).getJoint());
							System.out.println("Thank you for your decision!");
						}
					} 
					
					loginMenuChoice = UserInputHandler.getInstance().getNewInt(1, 3);
				}
				ls.logOut();
			} else {
				System.out.println("That password is incorrect, please try again another time.");
				ls.logOut();
			
			}
		}
		return loggedIn;
	}
	
	public void logOut() {
		System.out.println("Thank you!");
		System.exit(0);
	}
	

	public static String getPermissionedLoginMenu(User user) {
		if (user.getAccessLevel() < 0) {
			return "1 - View your existing accounts\n2 - Apply for a new account or joint access\n"
					+ "3 - Logout";
		} else if (user.getAccessLevel() == 0) {
			return "1 - View all customers\n2 - View all applications\n3 - Logout";
		} else {
			return "1 - View all customers\n2 - View all applications\n3 - Logout\"";
		}
	}
	
}
