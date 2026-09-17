package Practice.MovieBookingSystem.DTOs;

import java.util.UUID;

public class Ticket {
    private UUID ticketId;
    private Booking booking;
    private String QR;

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    @Override
    public String toString() {
        return String.format("---- Ticket----\n\tTicket-id: %s" +
                "\n\tCinema: %s" +
                "\n\tMovie: %s" +
                "\n\tScreen: %s" +
                "\n\tAmount: %f" +
                "\n\tTicket Status: %s"+
                "\n\tTiming: %s - %s"+
                "\n\tSeats: %s\n\n"
                , ticketId.toString(), booking.getShow().getScreen().getCinema().getName(), booking.getShow().getMovie().getTitle(),
                booking.getShow().getScreen().getName(), booking.getAmount(), booking.getBookingStatus().toString(),
                booking.show.getStartTime().toString(), booking.show.getEndTime().toString(),
                booking.bookedSeat.toString());

    }
}
