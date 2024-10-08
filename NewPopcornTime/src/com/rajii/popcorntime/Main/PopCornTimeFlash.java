package com.rajii.popcorntime.Main;

import com.rajii.popcorntime.Login.UserLoginView;
import com.rajii.popcorntime.repo.AdminRepository;
import com.rajii.popcorntime.repo.MovieListRepository;
import com.rajii.popcorntime.repo.ShowTimeRepository;
import com.rajii.popcorntime.repo.TheaterRepository;

public class PopCornTimeFlash {

	private static PopCornTimeFlash popcorntime;
	private String appName = "POPCORNTIME";
	private String appVersion = "0.2.1";

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public static PopCornTimeFlash getInstance() {
		if (popcorntime == null) {
			popcorntime = new PopCornTimeFlash();
		}
		return popcorntime;
	}

	private void create() {
		UserLoginView loginView = new UserLoginView();
		loginView.init();
	}

	public static void main(String[] arg) {
		AdminRepository.getInstance().loadObject();
		// TheaterRepo.getInstance().loadObject();
		MovieListRepository.getInstance().loadObject();
		ShowTimeRepository.getInstance().loadObject();

		PopCornTimeFlash.getInstance().create();
	}
}
