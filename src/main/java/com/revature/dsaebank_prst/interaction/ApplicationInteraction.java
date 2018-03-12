package com.revature.dsaebank_prst.interaction;

import com.revature.dsaebank_prst.pojo.Account;
import com.revature.dsaebank_prst.pojo.Application;

public interface ApplicationInteraction {

	public int makeDecision(Application application);
	public Account newAccountApproved(Application application);
	
}
