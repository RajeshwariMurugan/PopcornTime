package com.rajii.popcorntime.payment;

import java.util.Scanner;

public class MobilePayment {
	Scanner scanner = new Scanner(System.in);

	public boolean processPayment(double amount) {
		System.out.println("Processing mobile payment...");

		System.out.println("Enter Mobile Number: ");
		String mobileNumber = scanner.nextLine();

		if (mobileNumber.length() != 10) {
			System.out.println("Invalid mobile number. Payment failed.");
			return false;
		}

		System.out.println("Enter OTP sent to your mobile: ");
		String otp = scanner.nextLine();

		if (!otp.equals("123456")) {
			System.out.println("Invalid OTP. Payment failed.");
			return false;
		}

		System.out.println("Payment of $" + amount + " processed successfully via Mobile Wallet!");
		return true;
	}
}
