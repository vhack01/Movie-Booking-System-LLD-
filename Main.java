package Practice.MovieBookingSystem;

import Practice.MovieBookingSystem.DTOs.Show;
import Practice.MovieBookingSystem.DTOs.Ticket;
import Practice.MovieBookingSystem.service.BookingService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService();
        List<Show> shows = bookingService.getShows(bookingService.getCity("Hyderabad"), "Pushpa 3");

//        System.out.println(shows);
        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(()-> {
                Ticket bookedTicket = bookingService.createBooking(shows.get(j), List.of("E2", "E3"));
                System.out.println(bookedTicket);
            }).start();
        }
//        Ticket bookedTicket = bookingService.createBooking(shows.get(3), List.of("E2", "E3"));
//        System.out.println(bookedTicket);

    }
}
