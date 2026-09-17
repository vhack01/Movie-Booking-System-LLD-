package Practice.MovieBookingSystem.service;

import Practice.MovieBookingSystem.DTOs.Cinema;
import Practice.MovieBookingSystem.DTOs.City;
import Practice.MovieBookingSystem.DTOs.Screen;
import Practice.MovieBookingSystem.DTOs.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieSearchService {

    public List<Show> findShow(City city, String title) {
        List<Show> showList = new ArrayList<>();

        for(Cinema cinema: city.getCinemaList()) {
            for(Screen screen: cinema.getScreenList()) {
                for(Show show: screen.getShowList()) {
                    if(show.getMovie().getTitle().equals(title)) {
                        showList.add(show);
                    }
                }
            }
        }

        return showList;
    }

    public Map<Cinema, List<Show>> groupShowByCinema(List<Show> showList) {
        Map<Cinema, List<Show>> cinemaMap = new HashMap<>();

        for(Show show: showList) {
            Cinema cinema = show.getScreen().getCinema();
            if(cinemaMap.containsKey(cinema)) {
                cinemaMap.get(cinema).add(show);
            }
            else {
                cinemaMap.put(cinema, new ArrayList<>(List.of(show)));
            }
        }
        return cinemaMap;
    }
}
