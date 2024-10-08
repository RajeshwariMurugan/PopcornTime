package com.rajii.popcorntime.userview;

import java.util.List;

import com.rajii.popcorntime.movieSetUpAdmin.ManageMovieModel;
import com.rajii.popcorntime.payment.MobilePayment;
import com.rajii.popcorntime.repo.MovieListRepository;
import com.rajii.popcorntime.repo.ShowTimeRepository;
import com.rajii.popcorntime.repo.TheaterRepository;
import com.rajii.popcorntime.dto.Movie;
import com.rajii.popcorntime.dto.SeatingArrangement;
import com.rajii.popcorntime.dto.Showtime;
import com.rajii.popcorntime.dto.Theater;

public class UserModel {
	private ManageMovieModel movieManager;

	public UserModel() {
		movieManager = new ManageMovieModel();
	}

	public List<Theater> getTheaters() {
		return TheaterRepository.getInstance().getTheaterList();
	}

	public List<Movie> getMoviesInTheater(Theater theater) {
		return movieManager.getMovies(theater);
	}

	public List<Showtime> getShowtimesForMovie(Movie movie) {
		return ShowTimeRepository.getInstance().getShowtimesByMovie(movie.getTitle());
	}

	public boolean isSeatAvailable(Showtime showtime, int row, int col) {
		SeatingArrangement seatingArrangement = showtime.getSeatingArrangement();
		return seatingArrangement.isSeatAvailable(row, col);
	}

	public void bookSeat(Showtime showtime, int row, int col) {
		SeatingArrangement seatingArrangement = showtime.getSeatingArrangement();
		if (seatingArrangement.isSeatAvailable(row, col)) {
			seatingArrangement.bookSeat(row, col);
			System.out.println("Seat at row " + row + " and column " + col + " has been booked for the movie '"
					+ showtime.getMovie().getTitle() + "'.");
		} else {
			System.out.println("Seat at row " + row + " and column " + col + " is already booked or invalid.");
		}
	}

	public boolean processPayment(Movie movie, Theater theater, Showtime showtime) {
		MobilePayment pay = new MobilePayment();
		pay.processPayment(movie.getPrice());

		System.out.println("Payment of $" + movie.getPrice() + " for movie '" + movie.getTitle()
				+ "' has been processed successfully.");
		System.out.println(
				showtime.getShowtime() + " " + theater.getTheaterName() + "is waiting for " + movie.getTitle());
		return true;
	}
}
