package com.revature.dsaebank_prst.dao;

import java.util.List;
import com.revature.dsaebank_prst.pojo.Application;

public interface ApplicationDao {
	
	public void createApplication(Application application);
	public List<Application> retrieveAllOpenApplications();
	public Application retrieveApplication(String username);
	public void applicationDecision(Application application);
	
}
