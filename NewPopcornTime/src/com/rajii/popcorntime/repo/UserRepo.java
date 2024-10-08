package com.rajii.popcorntime.repo;

import java.util.ArrayList;
import java.util.List;

import com.rajii.popcorntime.dto.User;

public class UserRepo {
	private static UserRepo users;
	private static List<User> userList = new ArrayList<>();

	// Static block to add default users for testing
	static {
		UserRepo userRepo = getInstance();
		userRepo.addUser(new User("Karthick", "2801"));
		userRepo.addUser(new User("Gokila", "3401"));
	}

	// Singleton instance
	public static UserRepo getInstance() {
		if (users == null) {
			users = new UserRepo();
		}
		return users;
	}

	// Method to add a user
	public void addUser(User user) {
		userList.add(user);
	}

	// Method to validate user based on username and password
	public boolean validateUser(String userName, String password) {
		for (User user : userList) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				return true; // User found with matching credentials
			}
		}
		return false; // No matching user found
	}
}
