package com.rajii.popcorntime.userview;

import java.util.List;
import java.util.Scanner;

import com.rajii.popcorntime.Login.UserLoginView;
import com.rajii.popcorntime.Main.PopCornTimeFlash;
import com.rajii.popcorntime.dto.Movie;
import com.rajii.popcorntime.dto.SeatingArrangement;
import com.rajii.popcorntime.dto.Showtime;
import com.rajii.popcorntime.dto.Theater;

public class UserView {
    private Scanner scanner;
    private UserModel userModel;

    public UserView() {
        scanner = new Scanner(System.in);
        userModel = new UserModel();
    }

    // Initialize the user interface
    public void init() {
        while (true) {
            System.out.println("\n--- Welcome to " + PopCornTimeFlash.getInstance().getAppName() + " ---");
            System.out.println("1. View Theaters\n2. Select Theater and Movie Details\n9. Logout\n0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewTheaters();
                    break;
                case 2:
                    selectTheaterAndMovie();
                    break;
                case 9:
                    logout();
                    return; // Go back to login
                case 0:
                    System.out.println("Exiting the application. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // View available theaters
    private void viewTheaters() {
        List<Theater> theaters = userModel.getTheaters();
        if (theaters.isEmpty()) {
            System.out.println("No theaters available.");
        } else {
            System.out.println("\nAvailable Theaters:");
            for (int i = 0; i < theaters.size(); i++) {
                System.out.println((i + 1) + ". " + theaters.get(i).getTheaterName());
            }
        }
    }

    // Logout the user
    private void logout() {
        System.out.println("\n-- You have successfully logged out --\n");
        new UserLoginView().init(); // Go back to login
    }

    // Select a theater and movie
    private void selectTheaterAndMovie() {
        while (true) {
            List<Theater> theaters = userModel.getTheaters(); // Get list of theaters

            if (theaters.isEmpty()) {
                System.out.println("No theaters available.");
                return;
            }

            int theaterNumber = displayTheaters(theaters); // Display theaters and get user input

            if (theaterNumber == 0) {
                System.out.println("Returning to the main menu...");
                return; // Back option
            }

            Theater selectedTheater = theaters.get(theaterNumber - 1); // Get selected theater
            List<Movie> movies = userModel.getMoviesInTheater(selectedTheater);

            if (movies.isEmpty()) {
                System.out.println("No movies available in this theater.");
            } else {
                int movieNumber = displayMovies(movies, selectedTheater); // Display movies and get user input

                if (movieNumber == 0) {
                    System.out.println("Returning to theater selection...");
                    continue; // Go back to theater selection
                }

                Movie selectedMovie = movies.get(movieNumber - 1);
                List<Showtime> showtimes = userModel.getShowtimesForMovie(selectedMovie);

                if (showtimes.isEmpty()) {
                    System.out.println("No showtimes available for this movie.");
                } else {
                    int showtimeNumber = displayShowtimes(showtimes); // Display showtimes and get user input

                    if (showtimeNumber == 0) {
                        System.out.println("Returning to movie selection...");
                        continue; // Go back to movie selection
                    }

                    Showtime selectedShowtime = showtimes.get(showtimeNumber - 1);
                    selectSeatAndProcessPayment(selectedShowtime, selectedMovie, selectedTheater); // Select seat and process payment
                }
            }
        }
    }

    // Display theaters in a formatted way
    private int displayTheaters(List<Theater> theaters) {
        System.out.println("\nAvailable Theaters:");
        System.out.printf("%-20s %-30s%n", "Theater Number", "Theater Name");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < theaters.size(); i++) {
            System.out.printf("%-20d %-30s%n", (i + 1), theaters.get(i).getTheaterName());
        }

        System.out.println("---------------------------------------------------------------");
        System.out.print("Enter theater number to select a movie (or 0 to go back): ");
        return scanner.nextInt();
    }

    // Display movies for the selected theater
    private int displayMovies(List<Movie> movies, Theater selectedTheater) {
        System.out.println("\nMovies in " + selectedTheater.getTheaterName() + ":");
        for (int i = 0; i < movies.size(); i++) {
            System.out.printf("%d. %s (Price: $%.2f)\n", (i + 1), movies.get(i).getTitle(), movies.get(i).getPrice());
        }

        System.out.print("Enter movie number to view showtimes (or 0 to go back): ");
        return scanner.nextInt();
    }

    // Display available showtimes for the selected movie
    private int displayShowtimes(List<Showtime> showtimes) {
        System.out.println("\nAvailable Showtimes:");
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), showtimes.get(i).getShowtime());
        }

        System.out.print("Select showtime number (or 0 to go back): ");
        return scanner.nextInt();
    }

    // Select seat and process payment
    private void selectSeatAndProcessPayment(Showtime selectedShowtime, Movie selectedMovie, Theater selectedTheater) {
        SeatingArrangement seatingArrangement = selectedShowtime.getSeatingArrangement();
        seatingArrangement.displaySeating();

        System.out.print("Enter row number for seat selection: ");
        int row = scanner.nextInt();
        System.out.print("Enter seat number for seat selection: ");
        int col = scanner.nextInt();

        // Check seat availability and book
        if (userModel.isSeatAvailable(selectedShowtime, row, col)) {
            userModel.bookSeat(selectedShowtime, row, col);
            if (userModel.processPayment(selectedMovie, selectedTheater, selectedShowtime)) {
                printTicket(selectedMovie, selectedTheater, selectedShowtime, row, col); // Print ticket details
            }
        } else {
            System.out.println("Seat already booked or invalid seat selection. Try again.");
        }
    }

    // Print the ticket details
    private void printTicket(Movie movie, Theater theater, Showtime showtime, int row, int col) {
        System.out.println("\n--- Ticket Details ---");
        System.out.printf("Theater: %s\n", theater.getTheaterName());
        System.out.printf("Movie: %s\n", movie.getTitle());
        System.out.printf("Showtime: %s\n", showtime.getShowtime());
        System.out.printf("Seat: Row %d, Column %d\n", row, col);
        System.out.printf("Price: $%.2f\n", movie.getPrice());
        System.out.println("----------------------");
        System.out.println("Thank you for booking with us!");
        
    }
}
