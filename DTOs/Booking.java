package Practice.MovieBookingSystem.DTOs;

import java.util.List;

public class Booking {
    private long bookingId;
    private double amount;
    Show show;
    User user;
    List<ShowSeat> bookedSeat;
    private BookingStatus bookingStatus;

    public Booking() {
        bookingStatus = BookingStatus.PENDING;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBookedSeat(List<ShowSeat> bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public long getBookingId() {
        return bookingId;
    }

    public Show getShow() {
        return show;
    }
}
