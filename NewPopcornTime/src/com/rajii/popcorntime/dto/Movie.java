package com.rajii.popcorntime.dto;

public class Movie {
	private String title;
	private String genre;
	private double price;

	public Movie(String title, String genre, double price) {
		this.title = title;
		this.genre = genre;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public double getPrice() {
		return price;
	}

	public void setGenre(String genre) {
		// TODO Auto-generated method stub
		this.genre = genre;

	}

	public void setTicketPrice(double price) {
		// TODO Auto-generated method stub
		this.price = price;

	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;

	}
}
