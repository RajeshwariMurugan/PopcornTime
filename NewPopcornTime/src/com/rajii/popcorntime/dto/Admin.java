package com.rajii.popcorntime.dto;

public class Admin {
	User user;

	public Admin(User user) {
		this.user = user;

	}

	public String getAdminName() {
		return user.getUserName();
	}

	public String getPassword() {
		return user.getPassword();
	}

}
