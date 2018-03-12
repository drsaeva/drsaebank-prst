package com.revature.dsaebank_prst.pojo;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.revature.dsaebank_prst.dao.DaoFactory;

public class AccountDaoTest {
	
	private static final Account testAccount = new Account(1234567890, 1, 20.00);
	private static final AccountDaoTest testAccountDao = new AccountDaoTest();
	private static List<Account> allAccounts;
	
	@After
	public void deleteTestInputs() {
		DaoFactory.getBankAcctDao().deleteAccount(testAccount.getAccountNumber());
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	// check balance of test account against values inserted into Accounts table
	@Test
	public void testCreateAndRetrieveAccount() {
		DaoFactory.getBankAcctDao().createAccount(testAccount);
		assertTrue(testAccount.getBalance()==DaoFactory.getBankAcctDao().retrieveAccount(1234567890L).getBalance());
	}
	
	// check that the list of all accounts at least contains 1 account after insertion of a new record into Accounts
	@Test
	public void testRetrieveAllAccounts() {
		DaoFactory.getBankAcctDao().createAccount(testAccount);
		List<Account> allAccounts = DaoFactory.getBankAcctDao().retrieveAllAccounts();	
		assertTrue(allAccounts.size() > 0);
	}
	
	// check that an account inserted is properly deleted
	@Test
	public void testCreateAndDeleteAccount() {
		DaoFactory.getBankAcctDao().createAccount(testAccount);
		DaoFactory.getBankAcctDao().deleteAccount(testAccount.getAccountNumber());
		assertTrue(DaoFactory.getBankAcctDao().retrieveAccount(testAccount.getAccountNumber()).getBalance() != testAccount.getBalance());
	}
	
}
