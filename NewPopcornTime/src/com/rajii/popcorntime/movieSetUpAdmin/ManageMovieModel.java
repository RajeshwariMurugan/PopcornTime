package com.rajii.popcorntime.movieSetUpAdmin;

import java.util.List;

import com.rajii.popcorntime.dto.Movie;
import com.rajii.popcorntime.dto.Showtime;
import com.rajii.popcorntime.dto.Theater;
import com.rajii.popcorntime.repo.MovieListRepository;
import com.rajii.popcorntime.repo.ShowTimeRepository;
import com.rajii.popcorntime.repo.TheaterRepository;

public class ManageMovieModel {

	private List<Theater> theaters;

	public ManageMovieModel() {
		theaters = TheaterRepository.getInstance().getTheaterList();
	}

	// ______________________________________________________________________
	public void showTheaterList() {
		if (theaters.isEmpty()) {
			System.out.println("No theaters available.");
		} else {
			System.out.println("\nAvailable Theaters:");
			for (int i = 0; i < theaters.size(); i++) {
				System.out.println((i + 1) + ". " + theaters.get(i).getTheaterName());
			}
		}
	}

	public Theater getTheaterById(int theaterId) {
		if (theaterId > 0 && theaterId <= theaters.size()) {
			return theaters.get(theaterId - 1);
		}
		return null;
	}
	

//______________________________________________________________________________-
	// Add a new theater
	public void addTheater(Theater theater) {
		//theaters.add(theater);
		TheaterRepository.getInstance().addTheater(theater);
	}

	public void addMovie(Theater theater, Movie movie) {
		//theater.addMovie(movie); // Add the movie to the theater's movie list
		MovieListRepository.getInstance().addMovie(movie);
	}
	
	public List<Movie> getMovies(Theater theater) {
		return MovieListRepository.getInstance().getMovieList();
	}

	
	//_________________________________________________________________________________
	public void removeMovie(Theater theater, int movieNumber) {
	    List<Movie> movies = MovieListRepository.getInstance().getMovieList();
	      if (movieNumber > 0 && movieNumber <= movies.size()) {
	    	  	MovieListRepository.getInstance().removeMovie(movies.get(movieNumber-1));
	    } else {
	        System.out.println("Invalid movie number.");
	    }
	}

	
//_____________________________________________________________________________________________________________________

	public void viewShowTime(Theater theater, int movieNumber, String showTime) {
		List<Movie> movies = theater.getMovies();

		if (movies.isEmpty()) {
			System.out.println("No movies available in this theater.");
			return;
		}

		if (movieNumber > 0 && movieNumber <= movies.size()) {
			Movie movie = movies.get(movieNumber - 1);
			Showtime newShowtime = new Showtime(theater, movie, showTime);
			ShowTimeRepository.getInstance().addShowTime(newShowtime);
			theater.addShowtime(newShowtime);
			System.out.println("Showtime '" + showTime + "' added for movie '" + movie.getTitle() + "' in theater '"
					+ theater.getTheaterName() + "'.");
		} else {
			System.out.println("Invalid movie number.");
		}
	}

	public void addShowTime(Theater selectedTheater, int movieNumber, String showTime) {
		    if (selectedTheater == null) {
		        System.out.println("Selected theater is invalid.");
		        return;
		    }
		    
		    List<Movie> theaterMovies = selectedTheater.getMovies();
		    if (movieNumber <= 0 || movieNumber > theaterMovies.size()) {
		        System.out.println("Invalid movie number.");
		        return;
		    }
		    Movie selectedMovie = theaterMovies.get(movieNumber - 1); // -1 because list is zero-indexed

		    Showtime newShowtime = new Showtime(selectedTheater, selectedMovie, showTime);


		    System.out.println("Showtime added: " + selectedMovie.getTitle() + 
		                       " at " + showTime + " in " + selectedTheater.getTheaterName() + ".");
		}

		
	}


