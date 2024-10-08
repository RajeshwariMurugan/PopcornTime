package com.rajii.popcorntime.repo;

import java.util.ArrayList;
import java.util.List;

import com.rajii.popcorntime.dto.Admin;
import com.rajii.popcorntime.dto.Movie;
import com.rajii.popcorntime.dto.User;

public class AdminRepository {

	private static AdminRepository admin;
	private List<Admin> adminList = new ArrayList<>();

	public static AdminRepository getInstance() {
		if (admin == null) {
			admin = new AdminRepository();
		}
		return admin;
	}

	public List<Admin> getAdmin() {
		return adminList;
	}

	public void addAdmin(Admin admin) {
		adminList.add(admin);
	}

	public boolean validateAdmin(String adminName, String password) {
		for (Admin mem : adminList) {
			if (mem.getAdminName().equals(adminName) && mem.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public void loadObject() {
		Admin admin = new Admin(new User("Rajii", "4701"));
		adminList.add(admin);

	}
}
