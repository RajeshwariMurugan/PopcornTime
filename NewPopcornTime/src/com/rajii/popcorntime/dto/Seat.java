package com.rajii.popcorntime.dto;

public class Seat {
	private int row;
	private int col;
	private boolean isBooked;
	char[][] arr = new char[20][20];

	public Seat(int row, int col) {
		this.row = row;
		this.col = col;
		this.isBooked = false;
	}

	// Getters and Setters
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void bookSeat() {
		if (!isBooked) {
			this.isBooked = true;
			System.out.println("Seat at Row " + row + ", Col " + col + " is now booked.");
		} else {
			System.out.println("Seat at Row " + row + ", Col " + col + " is already booked.");
		}
	}
}
