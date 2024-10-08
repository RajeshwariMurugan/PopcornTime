package com.rajii.popcorntime.repo;

import java.util.ArrayList;
import java.util.List;
import com.rajii.popcorntime.dto.Movie;

public class MovieListRepository {

	private static MovieListRepository movielist;
	private List<Movie> movies = new ArrayList<>();

	private MovieListRepository() {
	}

	public static MovieListRepository getInstance() {
		if (movielist == null) {
			movielist = new MovieListRepository();
		}
		return movielist;
	}

	public List<Movie> getMovieList() {
		return movies;
	}

	public boolean addMovie(Movie movie) {
		if (alreadyExistsMovie(movie.getTitle())) {
			System.out.println("Movie already exists in the list.");
			return false; // Movie already exists
		}
		return movies.add(movie); // Successfully added
	}

	// Check if a movie with the given name already exists
	public boolean alreadyExistsMovie(String name) {
		for (Movie movie : movies) {
			if (movie.getTitle().equalsIgnoreCase(name)) {
				return true; // Movie found
			}
		}
		return false; // No movie found with the same name
	}

	// Load some default movies into the repository
	public void loadObject() {
		movies.add(new Movie("Vel", "Family", 120.0));
		movies.add(new Movie("Singam", "Family", 120.0));
		movies.add(new Movie("Thuppakki", "Favorite", 120.0));
	}

	// Update a movie in the list (for future implementation)
	public void updateMovie(Movie selectedMovie) {
		for (Movie movie : movies) {
			if (movie.getTitle().equalsIgnoreCase(selectedMovie.getTitle())) {
				movie.setTitle(selectedMovie.getTitle());
				movie.setGenre(selectedMovie.getGenre());
				movie.setTicketPrice(selectedMovie.getPrice());
				 System.out.println("Movie updated successfully.");
				return;
			}
		}
		System.out.println("Movie not found for update.");
	}

	// Remove a movie from the repository
	public void removeMovie(Movie movieToRemove) {
		if (movies.remove(movieToRemove)) {
			System.out.println("Movie '" + movieToRemove.getTitle() + "' removed successfully.");
		} else {
			System.out.println("Movie not found.");
		}
	}
}
