package com.revature.dsaebank_prst.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputHandler {
	private static UserInputHandler uih = null;
	private Scanner sc = null;
	
	private UserInputHandler() {
		this.sc = new Scanner(System.in);
	}
	
	//synch to prevent issues stemming from race conditions
	public static synchronized UserInputHandler getInstance() {
		// ensure that uih can be initialized only if it is null, otherwise it is permanently set
		if (uih == null) uih = new UserInputHandler();
		return uih;
	}
	
	// return singleton-owned scanner for operations
	public synchronized String getNewString() {
		boolean badInput=true;
		do {
			try {
				
				String s = ""; 
				s = sc.nextLine();
				if (s.length() > 0 && s.toLowerCase().matches(".*\\w+.*")) {
					return s;
				}
			} catch (InputMismatchException e) {
				System.out.println("You've provided an improper input. Please provide a string of "
						+ "\nalphabetical characters relevant to the requested input:");
				sc.next();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} while (badInput);
		return null;
	}
	
	public synchronized int getNewInt(int min, int max) {
		boolean badInput=true;
		do {
			try {

				int i = -4; 
				i = sc.nextInt();;
				if (i != -4.0 && i >= min && i <=max) return i;
			} catch (InputMismatchException e) {
				System.out.println("You've provided an improper input. Please input a single "
						+ "\ndigit corresponding to one of the options you've been provided with:");
				sc.next();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} while (badInput);
		return 0;
	}
	
	public synchronized double getNewDouble() {
		boolean badInput=true;
		do {
			try {
				double d = -4.0; 
				d = sc.nextDouble();
				if (d != -4.0 && d > 0) return d;
			} catch (InputMismatchException e) {
				System.out.println("You've provided an improper input. Please provide "
						+ "\na quantity of USD - a number with up to two decimal places:");
				sc.next();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} while (badInput);
		return 0.0;
	}
	
	public synchronized long getNewLong() {
		boolean badInput=true;
		do {
			try {
				long l = -4L; 
				l = sc.nextLong();
				if (l != -4 && l > 999999999L && l < 10000000000L) return l;
				else {
					System.out.println("You've provided an improper input. Please provide "
							+ "\nan account number with 10 digits:");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("You've provided an improper input. Please provide "
						+ "\nan account number with 10 digits:");
				sc.next();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} while (badInput);
		return 0L;
	}
}
