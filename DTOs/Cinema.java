package Practice.MovieBookingSystem.DTOs;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private final long id;
    private final String name;
    private final City city;
    private final List<Screen> screenList;

    public Cinema(long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
        screenList = new ArrayList<>();
    }

    public void addScreenList(List<Screen> screenList) {
        this.screenList.addAll(screenList);
    }

    public List<Screen> getScreenList() {
        return screenList;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("Cinema: {id: %d, name: %s}", id, name);
    }

}
