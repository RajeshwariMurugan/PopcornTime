package com.rajii.popcorntime.dto;

public class SeatingArrangement {
	private Seat[][] seats;
	private int rows;
	private int cols;

	public SeatingArrangement(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		seats = new Seat[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				seats[i][j] = new Seat(i, j);
			}
		}
	}

	public SeatingArrangement() {
		// TODO Auto-generated constructor stub
	}

	public void displaySeating() {
		System.out.println("\nSeating Arrangement:");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (seats[i][j].isBooked()) {
					System.out.print("[B] ");
				} else {
					System.out.print("[O] ");
				}
			}
			System.out.println();
		}
	}

	public void bookSeat(int row, int col) {
		if (row >= 0 && row < rows && col >= 0 && col < cols) {
			seats[row][col].bookSeat();
		} else {
			System.out.println("Invalid seat selection.");
		}
	}

	public boolean isSeatAvailable(int row, int col) {
		if (row >= 0 && row < rows && col >= 0 && col < cols) {
			return !seats[row][col].isBooked();
		} else {
			System.out.println("Invalid seat selection.");
			return false;
		}
	}
}
