package com.revature.dsaebank_prst.interaction;

import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.pojo.Application;
import com.revature.dsaebank_prst.pojo.User;

public interface ApplicationInteraction {
	// employee methods
	public int makeDecision(Application application, int accessLevel);
	public Account newAccountApproved(Application application, int accessLevel);
	public Account updateAccount(Application application, User user);
	
	// customer methods
	public Application applicationProcess(User user);
	
}
