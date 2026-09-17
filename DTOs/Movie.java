package Practice.MovieBookingSystem.DTOs;

public class Movie {
    private final Long id;
    private final String title;
    private final long durationInMins;
    private final String genre;
    private final String language;

    public Movie(Long id, String title, String language, long durationInMins, String genre) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.durationInMins = durationInMins;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public long getDurationInMins() {
        return durationInMins;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", name=" + title + '}';
    }
}
