package com.rajii.popcorntime.dto;

public class Showtime {
	private Theater theater;
	private Movie movie;
	private String time;
	private String showtime;
	private SeatingArrangement seatingArrangement;

	// Corrected constructor that initializes the seating arrangement properly
	public Showtime(String showtime, Movie movie, SeatingArrangement seatingArrangement) {
		this.showtime = showtime;
		this.movie = movie;
		this.seatingArrangement = seatingArrangement;
		Showtime showtime1 = new Showtime("10:00 AM", movie, seatingArrangement);
	}

	public Showtime(Theater theater, Movie movie, String time) {
		this.theater = theater;
		this.movie = movie;
		this.time = time;
		this.seatingArrangement = new SeatingArrangement(10, 10); // Default seating arrangement
	}

	// Getters
	public String getShowtime() {
		return showtime;
	}

	public Movie getMovie() {
		return movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public SeatingArrangement getSeatingArrangement() {
		return seatingArrangement;
	}
}
