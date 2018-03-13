package com.revature.dsaebank_prst.interaction;

import com.revature.dsaebank_prst.dao.DaoFactory;
import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.pojo.Application;
import com.revature.dsaebank_prst.pojo.User;
import com.revature.dsaebank_prst.util.UserInputHandler;

public class ApplicationInteractionImpl implements ApplicationInteraction {
	
	public int makeDecision(Application application, int accessLevel) {
		if (accessLevel != -1) {
			System.out.println("Username\t Single(0)/Joint(1)\tJointAccountNumber\n"
					+ "----------------------------------------------------------------");
			 if (application.getApprovalStatus()==0)
				System.out.println(application.getUsername()+"\t"+application.getJoint()+"\t\t"+application.getJointAccountNumber());
			 
			System.out.println("Please decide whether to approve or deny the current application:\n");
			System.out.println("1 - Deny\n2 - Approve");
			int decision = UserInputHandler.getInstance().getNewInt(1, 2);
			if (decision == 1) decision-=2;
			else decision -= 1;
			
			return decision;
		}
		else return 0;
	}

	public Account newAccountApproved(Application application, int accessLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account updateAccount(Application application, User user) {
		if (user.getAccessLevel() >= 0 && application.getApprovalStatus() == 1) {
			Account account = DaoFactory.getBankAcctDao().retrieveAccount(application.getJointAccountNumber());
			return account;
		}
		return null;
	}

	public Application applicationProcess(User user) {
		if (user.getAccessLevel() != -1) return null;
		else {
			
			System.out.println("Thank you for your interest in appplying for a new account or \n"
					+ "joint access to an existing account!\nPlease provide details as requested:\n"
					+ "Is the account you are applying for a single account, or do you wish to \n"
					+ "apply for joint access to an existing account?\n"
					+ "Please select a number from the following options:\n\n"
					+ "0 - Single\n"
					+ "1 - Joint\n"
					+ "Please answer below:\n");
			
			Application newApp = new Application();
			
			int jointReply = UserInputHandler.getInstance().getNewInt(0, 1);
			long aNum = 0L;
			System.out.println("Thank you!\n");
			if (jointReply == 1) {
				System.out.println("Please provide the account number you'd like to apply for joint\n"
						+ "access to. Remember, account numbers are 10-digit numbers.\n"
						+ "Please provide the account number below:\n");
				aNum = UserInputHandler.getInstance().getNewLong();
				System.out.println("Thank you!\n");
			}
			System.out.println("A bank employee will respond to your application as soon as possible.\n"
					+ "If you are approved, your electronic access to the account will be granted \n"
					+ "immediately! Thank you for your interest in an account.");
			newApp.setUsername(user.getUsername());
			newApp.setJoint(jointReply);
			if (jointReply == 1) newApp.setJointAccountNumber(aNum);
			return newApp;
		}
	}

}
