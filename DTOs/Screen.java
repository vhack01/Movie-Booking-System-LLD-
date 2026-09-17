package Practice.MovieBookingSystem.DTOs;


import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final long id;
    private final String name;
    private final List<Show> showList;
    private final List<Seat> seatList;
    private final Cinema cinema;

    public Screen(long id, String name, Cinema cinema) {
        this.id = id;
        this.name = name;
        this.cinema = cinema;
        this.showList = new ArrayList<Show>();
        this.seatList = new ArrayList<>();
    }

    public void addSeatList(List<Seat> seats) {
        this.seatList.addAll(seats);
    }

    public void addShowList(List<Show> showList) {
        this.showList.addAll(showList);
    }

    public long getId() {
        return id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public List<Show> getShowList() {
        return showList;
    }

    public String getName() {
        return name;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    @Override
    public String toString() {
        return "Screen{" + "id=" + id + ", name=" + name + '}';
    }
}
