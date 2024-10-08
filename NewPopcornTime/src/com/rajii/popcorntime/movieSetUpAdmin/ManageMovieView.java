package com.rajii.popcorntime.movieSetUpAdmin;

import java.util.List;
import java.util.Scanner;

import com.rajii.popcorntime.Login.UserLoginView;
import com.rajii.popcorntime.Main.PopCornTimeFlash;
import com.rajii.popcorntime.dto.Movie;
import com.rajii.popcorntime.dto.Showtime;
import com.rajii.popcorntime.dto.Theater;
import com.rajii.popcorntime.repo.MovieListRepository;
import com.rajii.popcorntime.repo.ShowTimeRepository;
import com.rajii.popcorntime.repo.TheaterRepository;
import com.rajii.popcorntime.userview.UserView;

public class ManageMovieView {

	private ManageMovieModel moviesetup;
	private Scanner scanner;
	private Theater selectedTheater;

	public ManageMovieView() {
		moviesetup = new ManageMovieModel();
		scanner = new Scanner(System.in);
	}

	public void onCreate() {
		while (true) {

			System.out.println("==============================================================================");
			System.out.println("\n\nLogin successful...\n\n Hello Admin!---- welcome to "
					+ PopCornTimeFlash.getInstance().getAppName() + " - v"
					+ PopCornTimeFlash.getInstance().getAppVersion() + "----");
			System.out.println("==============================================================================");
			
			System.out.println("===========================ADMIN PORTAL=======================================");


			System.out.println(
					"\n 1. Select Theater\n 2. Add Movie\n 3. Show Movies\n 4. Remove Movie\n 5. Add Showtime\n 6. Add Theater\n 7.Print all details\n 9. Logout \n 0. Exit \n Enter your Choice:");
			System.out.println("=  ==== ====== ========  ======== ========= ========== =======");
				int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				selectTheater();
				break;
			case 2:
				if (isTheaterSelected()) {
					addMovie();
				}
				break;
			case 3:
				if (isTheaterSelected()) {
					showMovies();
				}
				break;
			case 4:
				if (isTheaterSelected()) {
					removeMovie();
				}
				break;
			case 5:
				if (isTheaterSelected()) {
					addShowTime();
				}
				break;
			case 6:
				addTheater();
				break;
			case 7:
				printTheaterDetails();
				break;
			case 9:
				logout();
				return;
			case 0:
				System.out.println("\n-- Thanks for using " + PopCornTimeFlash.getInstance().getAppName() + " --");
				return;
			default:
				System.out.println("\nPlease enter a valid choice\n");
			}
		}
	}

	private void addTheater() {
		System.out.println("Enter Theater Name: ");
		String theaterName = scanner.nextLine();
		System.out.println("Enter Theater Id: ");
		int theaterId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Theater Address: ");
		String theaterAddress = scanner.nextLine();
		moviesetup.addTheater(new Theater(theaterName, theaterId, theaterAddress));
		System.out.println("Theater '" + theaterName + "' added successfully.");
	}

	private boolean isTheaterSelected() {
		if (selectedTheater == null) {
			System.out.println("\nPlease select a theater first!\n");
			return false;
		}
		return true;
	}

	private void selectTheater() {
		moviesetup.showTheaterList();
		System.out.println("\nSelect the theater from the list below:");

		System.out.println("Enter theater number: ");
		int theaterNumber = scanner.nextInt();
		scanner.nextLine();
		selectedTheater = moviesetup.getTheaterById(theaterNumber);
		if (selectedTheater != null) {
			System.out.println("\nTheater '" + selectedTheater.getTheaterName() + "' selected.\n");
		} else {
			System.out.println("\nInvalid theater number. Try again.\n");
		}
	}

	private void addMovie() {
		Movie newMovie = getMovie();
		moviesetup.addMovie(selectedTheater, newMovie);
//		System.out.println(
//				"Movie '" + newMovie.getTitle() + "' added to theater '" + selectedTheater.getTheaterName() + "'.");
	}

	private Movie getMovie() {
		System.out.println("Enter Movie Name: ");
		String movieName = scanner.nextLine();
		System.out.println("Enter Movie Genre: ");
		String movieGenre = scanner.nextLine();
		System.out.println("Enter Ticket Price: ");
		double ticketPrice = scanner.nextDouble();
		scanner.nextLine();

		return new Movie(movieName, movieGenre, ticketPrice);
	}

	private void showMovies() {
		List<Movie> movies = moviesetup.getMovies(selectedTheater);
		if (movies.isEmpty()) {
			System.out.println("No movies available in this theater.");
		} else {
			System.out.println("Movies in '" + selectedTheater.getTheaterName() + "':");
			for (int i = 0; i < movies.size(); i++) {
				System.out.println((i + 1) + ". " + movies.get(i).getTitle());
			}
		}
	}

	private void removeMovie() {
		showMovies();
		System.out.println("Enter the movie number to remove: ");
		int movieNumber = scanner.nextInt();
		scanner.nextLine();
		moviesetup.removeMovie(selectedTheater, movieNumber);
	}

	private void addShowTime() {
		showMovies();
		System.out.println("Enter the movie number to add a showtime: ");
		int movieNumber = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter the showtime (e.g., 7:30 PM): ");
		String showTime = scanner.nextLine();

		moviesetup.addShowTime(selectedTheater, movieNumber, showTime);
	}
	
	
	    public void printTheaterDetails() {
	    	
	    	ShowTimeRepository.getInstance().displayShowtimes();;
//        List<Theater> theaters = TheaterRepository.getInstance().getTheaterList();
	        
	        System.out.println("-------------------------------------------------------------------");
//	        System.out.printf("%-20s %-15s %-15s %-15s", "Theater Name", "Movie Title", "Genre", "Showtime");
//	        System.out.println("-------------------------------------------------------------------");
//
//	        for (Theater theater : theaters) {
//	            List<Movie> movies = theater.getMovies();
//	            for (Showtime showtime : ShowTimeRepository.getInstance().getShowtime()) {
//	                if (showtime.getTheater().equals(theater)) {
//	    	            System.out.println();
//
//	                      System.out.printf("%-20s %-15s %-15s %-15s",
//	                            theater.getTheaterName(),
//	                            showtime.getMovie().getTitle(),
//	                            showtime.getMovie().getGenre(),
//	                            showtime.getShowtime());
//	                }
//	            }
//	        }

	        System.out.println("-------------------------------------------------------------------");
	    }
	


	private void logout() {
		System.out.println("\n-- You are logged out successfully -- \n\n");
		new UserLoginView().init();
	}
}
