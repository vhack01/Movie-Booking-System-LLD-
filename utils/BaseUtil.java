package Practice.MovieBookingSystem.utils;

import Practice.MovieBookingSystem.DTOs.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseUtil {

    private static long showSeatIdCounter = 1;

    public static Map<String, City> getCities() {
        Map<String, City> cities = new HashMap<>();

        City hyderabad = new City(1, "Hyderabad");
        List<Cinema> cinemaList = createCinemas(hyderabad);
        hyderabad.addCinemaList(cinemaList);

        cities.put(hyderabad.getName(), hyderabad);
        return cities;
    }

    private static List<Cinema> createCinemas(City city) {
        Cinema ambCinema = new Cinema(1, "AMB Cinemas", city);
        ambCinema.addScreenList(createScreens(ambCinema));

        Cinema aac = new Cinema(2, "AAC Cinemas", city);
        aac.addScreenList(createScreens(aac));

        Cinema inorbit = new Cinema(3, "Inorbit Cinemas", city);
        inorbit.addScreenList(createScreens(inorbit));

        Cinema prashadCinema = new Cinema(4, "Prashad Cinemas", city);
        prashadCinema.addScreenList(createScreens(prashadCinema));

        List<Cinema> cinemaList = new ArrayList<>();
        cinemaList.add(ambCinema);
        cinemaList.add(aac);
        cinemaList.add(inorbit);
        cinemaList.add(prashadCinema);
        return cinemaList;
    }

    private static List<Screen> createScreens(Cinema cinema) {
        List<Screen> screens = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Screen screen = new Screen(i, "Screen-" + i, cinema);

            List<Seat> seats = createSeats(screen);
            screen.addSeatList(seats);   // seats must exist BEFORE shows, since shows need them for ShowSeats

            List<Show> shows = createShows(screen);
            screen.addShowList(shows);

            screens.add(screen);
        }
        return screens;
    }

    private static List<Seat> createSeats(Screen screen) {
        List<Seat> seats = new ArrayList<>();
        int seatIdCounter = 1;

        // Rows A-B: REGULAR, Row C-D: PREMIUM, Row E: RECLINER — just an example layout
        char[] rows = {'A', 'B', 'C', 'D', 'E'};
        for (char row : rows) {
            SeatType type = (row == 'A' || row == 'B') ? SeatType.REGULAR
                    : (row == 'C' || row == 'D') ? SeatType.PREMIUM
                      : SeatType.RECLINER;

            for (int col = 1; col <= 10; col++) {
                String seatNo = row + String.valueOf(col);
                Seat seat = new Seat(seatNo, row, type);
                seats.add(seat);
            }
        }
        return seats;
    }

    private static List<Show> createShows(Screen screen) {
        List<Show> shows = new ArrayList<>();

        List<Movie> movies = createMovies(); // see note below
        Movie movie = movies.get(0);

        LocalDateTime[] slots = {
                LocalDateTime.of(2026, 2, 2, 10, 0),
                LocalDateTime.of(2026, 5, 3, 14, 0),
                LocalDateTime.of(2026, 4, 15, 18, 0),
                LocalDateTime.of(2026, 3, 27, 21, 30)
        };

        for (LocalDateTime start : slots) {
            LocalDateTime end = start.plusMinutes(movie.getDurationInMins());
            Show show = new Show(start, end, screen, movie);

            List<ShowSeat> showSeats = createShowSeats(show, screen.getSeatList());
            show.addShowSeatList(showSeats);
            shows.add(show);
        }
        return shows;
    }

    private static List<ShowSeat> createShowSeats(Show show, List<Seat> seats) {
        List<ShowSeat> showSeats = new ArrayList<>();
        for (Seat seat : seats) {
            double basePrice = getBasePriceForType(seat.getSeatType());
            ShowSeat showSeat = new ShowSeat(showSeatIdCounter++, show, seat, basePrice);
            showSeats.add(showSeat);
        }
        return showSeats;
    }

    private static double getBasePriceForType(SeatType type) {
        switch (type) {
            case REGULAR: return 150.0;
            case PREMIUM: return 250.0;
            case RECLINER: return 400.0;
            default: throw new IllegalArgumentException("Unknown seat type: " + type);
        }
    }

    private static List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(1L, "Pushpa 3", "Telugu", 165, "Action"));
        movies.add(new Movie(2L, "War 2", "Hindi", 150, "Action-Thriller"));
        movies.add(new Movie(3L, "Kalki 2", "Telugu", 170, "Sci-Fi"));
        movies.add(new Movie(4L, "Jawan 2", "Hindi", 145, "Action"));

        return movies;
    }
}