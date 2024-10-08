package com.rajii.popcorntime.repo;

import java.util.ArrayList;
import java.util.List;
import com.rajii.popcorntime.dto.Movie;
import com.rajii.popcorntime.dto.Theater;

public class TheaterRepository {

    private static TheaterRepository theaterRepo;
    private List<Theater> theaters;

    private TheaterRepository() {
        theaters = new ArrayList<>();
        loadObject(); // Load theaters with specific movies
    }

    public static TheaterRepository getInstance() {
        if (theaterRepo == null) {
            theaterRepo = new TheaterRepository();
        }
        return theaterRepo;
    }

    public List<Theater> getTheaterList() {
        return theaters;
    }

    public void addTheater(Theater theater) {
        theaters.add(theater);
    }

    public void removeTheater(Theater theater) {
        if (theaters.remove(theater)) {
            System.out.println("Theater '" + theater.getTheaterName() + "' removed successfully.");
        } else {
            System.out.println("Theater not found.");
        }
    }

    public Theater getTheaterById(int theaterId) {
        if (theaterId > 0 && theaterId <= theaters.size()) {
            return theaters.get(theaterId - 1);
        }
        return null; // Invalid theater ID
    }

    public void addMovieToTheater(int theaterId, Movie movie) {
        Theater theater = getTheaterById(theaterId);
        if (theater != null) {
            theater.addMovie(movie);
        } else {
            System.out.println("Theater not found.");
        }
    }

    public void loadObject() {
        // Create some default movies
        Movie movie1 = new Movie("Vel", "Action", 150.0);
        Movie movie2 = new Movie("Singam: Endgame", "Action", 180.0);
        Movie movie3 = new Movie("Thuppakki", "Crime", 120.0);

        Movie movie4 = new Movie("Kutty", "Love", 195.0);
        Movie movie5 = new Movie("Yaradi ni mohini", "Love", 152.0);
        Movie movie6 = new Movie("Santhosh Subramaniyam", "Love", 142.0);

        Movie movie7 = new Movie("Samuthiram", "SibilingsLove", 155.0);
        Movie movie8 = new Movie("Namma Veetu pillai", "Sister Love", 136.0);
        Movie movie9 = new Movie("Varuthapadatha valibar sangam", "Comedy", 175.0);

        // Adding theaters with specific movies
        Theater theater1 = new Theater("GRB", 4, "Surandai");
        theater1.addMovie(movie1);
        theater1.addMovie(movie2);
        theater1.addMovie(movie3);

        Theater theater2 = new Theater("3Roses", 7, "Chennai");
        theater2.addMovie(movie4);
        theater2.addMovie(movie5);
        theater2.addMovie(movie6);

        Theater theater3 = new Theater("Friends", 5, "Bangalore");
        theater3.addMovie(movie7);
        theater3.addMovie(movie8);
        theater3.addMovie(movie9);

        // Add theaters to the list
        addTheater(theater1);
        addTheater(theater2);
        addTheater(theater3);
        // Add more theaters and their movies as needed
    }
}
