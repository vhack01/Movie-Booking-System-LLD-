package Practice.MovieBookingSystem.DTOs;

import Practice.MovieBookingSystem.exceptions.SeatNoAvailableException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Show {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    List<ShowSeat> showSeatList;
    private Screen screen;
    private Movie movie;

    private final Lock lock = new ReentrantLock();

    public Show(LocalDateTime startTime, LocalDateTime endTime, Screen screen, Movie movie) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.screen = screen;
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void addShowSeatList(List<ShowSeat> showSeatList) {
        this.showSeatList = showSeatList;
    }

    public List<ShowSeat> getShowSeatList() {
        return showSeatList;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<ShowSeat> lockSeat(List<String> selectedSeats) {
        lock.lock();
        try {
            List<ShowSeat> targetSeats = resolveSeats(selectedSeats);

            List<ShowSeat> lockedSoFar = new ArrayList<>();
            for(ShowSeat showSeat : targetSeats) {
                if(showSeat.tryBookingShowSeat()) {
                    lockedSoFar.add(showSeat);
                }
                else {
                    // Rollback all lockedSeat so far
                    for(ShowSeat s: lockedSoFar) {
                        s.cancelBooking();
                    }
                    throw new SeatNoAvailableException("Seat " + showSeat.getSeat().getSeatNo() + " unavailable");
                }
            }
            return lockedSoFar;
        }
        finally {
            lock.unlock();
        }
    }

    public boolean confirmSeats(List<ShowSeat> seats) {
        lock.lock();
        try {
            for(ShowSeat seat: seats) {
                if(seat.getSeatStatus() != SeatStatus.LOCKED) {
                    return false;
                }
            }

            for(ShowSeat seat: seats) {
                seat.confirmBooking();
            }
            return true;
        }
        finally {
            lock.unlock();
        }
    }

    public void releaseSeats(List<ShowSeat> seats) {
        lock.lock();
        try {
            for(ShowSeat seat: seats) {
                if(seat.getSeatStatus() == SeatStatus.LOCKED) {
                    seat.cancelBooking();
                }
            }
        }
        finally {
            lock.unlock();
        }
    }

    private List<ShowSeat> resolveSeats(List<String> selectedSeats) {
        Map<String, ShowSeat> bySeatNo = showSeatList.stream()
                .collect(Collectors.toMap(s -> s.getSeat().getSeatNo(), s -> s));
        List<ShowSeat> resolved = new ArrayList<>();

        for(String seatNo : selectedSeats) {
            ShowSeat seat = bySeatNo.get(seatNo);
            if(seat == null) {
                throw new IllegalArgumentException("No such seat on this show: " + seatNo);
            }
            resolved.add(seat);
        }
        return resolved;
    }

    public void renderAllShows(List<Show> shows) {
        for(Show show: shows) {
            Screen screen = show.getScreen();
            Cinema cinema = screen.getCinema();
            City city = cinema.getCity();

            System.out.println(city);
            System.out.println(cinema);
            System.out.println(screen);
            System.out.println(show);

        }
    }

    @Override
    public String toString() {
        long availableCount = showSeatList == null ? 0 :
                showSeatList.stream().filter(s -> s.getSeatStatus() == SeatStatus.AVAILABLE).count();

        return String.format(
                "Show[ movie=%s,\n cinema=%s,\n screen=%s,\n start=%s, end=%s,\n availableSeats=%d/%d]\n\n",
                movie.getTitle(),
                screen.getCinema().getName(),
                screen.getName(),
                startTime,
                endTime,
                availableCount,
                showSeatList == null ? 0 : showSeatList.size()
        );
    }
}
