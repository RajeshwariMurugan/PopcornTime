package com.rajii.popcorntime.dto;

public class Book {
	private Movie movie;
	private Showtime showtime;
	private double price;

	public Book(Movie movie, Showtime showtime, Seat seat, double price) {
		this.movie = movie;
		this.showtime = showtime;
		this.price = price;
	}

	
}
