package com.rajii.popcorntime.Login;

import com.rajii.popcorntime.movieSetUpAdmin.ManageMovieView;
import com.rajii.popcorntime.repo.AdminRepository;
import com.rajii.popcorntime.repo.UserRepo;

class UserLoginModel {
	private UserLoginView loginview;

	public UserLoginModel(UserLoginView userLoginView) {
		this.loginview = userLoginView;
	}

	public void validateUser(String userName, String password) {
		if (isValidAdmin(userName, password)) {
			ManageMovieView manageMovieView = new ManageMovieView();
			manageMovieView.onCreate();

		} else if (isValidUser(userName, password)) {
			loginview.onSuccess(userName);

		} else {
			loginview.onLoginFailed("Invalid User Name");
		}

	}

	private boolean isValidUser(String userName, String password) {
		return UserRepo.getInstance().validateUser(userName, password);
	}

	private boolean isValidAdmin(String AdminName, String password) {
		return AdminRepository.getInstance().validateAdmin(AdminName, password);
	}

}
