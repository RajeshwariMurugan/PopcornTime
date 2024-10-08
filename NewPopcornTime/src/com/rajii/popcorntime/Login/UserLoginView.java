package com.rajii.popcorntime.Login;

import java.util.Scanner;

import com.rajii.popcorntime.Main.PopCornTimeFlash;
import com.rajii.popcorntime.userview.UserView;

public class UserLoginView {
	Scanner sc = new Scanner(System.in);
	private UserLoginModel loginmodel;

	public UserLoginView() {
		loginmodel = new UserLoginModel(this);
	}

	public void init() {
		System.err.println("---  Welcome To" + PopCornTimeFlash.getInstance().getAppName() + " --- \nversion "
				+ PopCornTimeFlash.getInstance().getAppVersion());
		System.out.println("\n\nPlease login to proceed.");
		proceedLogin();
	}

	public void onSuccess(String userName) {
		System.out.println("==============================================================================");
		System.out.println(
				"\n\nLogin successful...\n\n Hello "+userName +"!---- welcome to " + PopCornTimeFlash.getInstance().getAppName()
						+ " - v" + PopCornTimeFlash.getInstance().getAppVersion() + "----");
		System.out.println("==============================================================================");

		UserView theaterSetupView = new UserView();
		theaterSetupView.init();
	}

	public void onLoginFailed(String alertText) {
		System.err.println(alertText);
		checkForLogin();
	}

	private void checkForLogin() {
		System.out.println("Do you try again? \nType Yes/No");
		String choice = sc.next();
		if (choice.equalsIgnoreCase("yes")) {
			proceedLogin();
		} else if (choice.equalsIgnoreCase("no")) {
			System.out.println("\n ---- Thanking You ----");
		} else {
			System.err.println("\nInvalid choice, Please enter valid choice.\n");
			checkForLogin();
		}
	}

	private void proceedLogin() {
		System.out.println("\nEnter the user name:");
		String userName = sc.next();
		System.out.println("Enter the password:");
		String password = sc.next();

		loginmodel.validateUser(userName, password);
	}
}
