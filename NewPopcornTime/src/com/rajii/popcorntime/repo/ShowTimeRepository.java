package com.rajii.popcorntime.repo;

import java.util.ArrayList;
import java.util.List;
import com.rajii.popcorntime.dto.Movie;
import com.rajii.popcorntime.dto.Showtime;
import com.rajii.popcorntime.dto.Theater;

public class ShowTimeRepository {
    private static ShowTimeRepository instance;
    private List<Showtime> showlist;
    private static String[] showtimes = {"10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM"};


    private ShowTimeRepository() {
        showlist = new ArrayList<>();
    }

    public static ShowTimeRepository getInstance() {
        if (instance == null) {
            instance = new ShowTimeRepository();
        }
        return instance;
    }

    public void addShowTime(Showtime showtimes) {
        showlist.add(showtimes);
    }

    public List<Showtime> getShowtime() {
        return showlist;
    }

    public List<Showtime> getShowtimesByMovie(String movieTitle) {
        List<Showtime> result = new ArrayList<>();
        for (Showtime showtime : showlist) {
            if (showtime.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                result.add(showtime);
            }
        }
        return result;
    }

    public void loadObject() {

        for (Theater t : TheaterRepository.getInstance().getTheaterList()) {
            for (Movie m : MovieListRepository.getInstance().getMovieList()) {
                for (String time : showtimes) {
                    addShowTime(new Showtime(t, m, time));
                }
            }
        }
    }

    public void displayShowtimes() {
        for (Showtime t : showlist) {
            System.out.println("Theater: " + t.getTheater().getTheaterName() +
                               ", Movie: " + t.getMovie().getTitle() + 
                               ", Showtime: " + t.getShowtime());
        }
    }
}
