package Practice.MovieBookingSystem.DTOs;

import java.util.ArrayList;
import java.util.List;

public class City {
    private long id;
    private String name;
    List<Cinema> cinemaList;

    public City(long id, String name) {
        this.id = id;
        this.name = name;
        cinemaList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void addCinema(Cinema cinema) {
        cinemaList.add(cinema);
    }

    public void addCinemaList(List<Cinema> cinemaList) {
        this.cinemaList.addAll(cinemaList);
    }

    public List<Cinema> getCinemaList() {
        return cinemaList;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name=" + name + '}';
    }
}
