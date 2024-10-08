package com.rajii.popcorntime.dto;

import java.util.ArrayList;
import java.util.List;

public class Theater {

	private String theaterName;
	private int theaterId;
	private String address;
	private List<Movie> movies;
	private List<Showtime> showtimes;

	public Theater(String theaterName, int theaterId, String address) {
		this.theaterName = theaterName;
		this.theaterId = theaterId;
		this.address = address;
		this.movies = new ArrayList<>();
		this.showtimes = new ArrayList<>();
	}

	// Default constructor
	public Theater() {
		this.movies = new ArrayList<>();
		this.showtimes = new ArrayList<>();
	}

	// Getters and setters
	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Get the list of movies playing in the theater
	public List<Movie> getMovies() {
		return movies;
	}

	// Add a movie to the theater
	public void addMovie(Movie movie) {
		movies.add(movie);
		System.out.println("Movie '" + movie.getTitle() + "' added to theater '" + this.theaterName + "'.");
	}

	// Remove a movie from the theater
	public void removeMovie(Movie movie) {
		if (movies.remove(movie)) {
			System.out.println("Movie '" + movie.getTitle() + "' removed from theater '" + this.theaterName + "'.");
		} else {
			System.out.println("Movie not found in theater.");
		}
	}

	// Get the list of showtimes in the theater
	public List<Showtime> getShowtimes() {
		return showtimes;
	}

	// Add a showtime to the theater
	public void addShowtime(Showtime showtime) {
		showtimes.add(showtime);
		System.out.println("Showtime '" + showtime.getShowtime() + "' added for movie '"
				+ showtime.getMovie().getTitle() + "' in theater '" + this.theaterName + "'.");
	}

	// Remove a showtime from the theater
	public void removeShowtime(Showtime showtime) {
		if (showtimes.remove(showtime)) {
			System.out.println(
					"Showtime '" + showtime.getShowtime() + "' removed from theater '" + this.theaterName + "'.");
		} else {
			System.out.println("Showtime not found in theater.");
		}
	}
}
