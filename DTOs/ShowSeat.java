package Practice.MovieBookingSystem.DTOs;

public class ShowSeat {
    private final long showSeatId;
    private SeatStatus seatStatus;
    private final Show show;
    private final Seat seat;
    private final double price;

    public ShowSeat(long showSeatId, Show show, Seat seat, double price) {
        this.showSeatId = showSeatId;
        this.seatStatus = SeatStatus.AVAILABLE;
        this.show = show;
        this.seat = seat;
        this.price = price;
    }

    public long getShowSeatId() {
        return showSeatId;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Show getShow() {
        return show;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public boolean tryBookingShowSeat() {
        if(seatStatus == SeatStatus.AVAILABLE) {
            seatStatus = SeatStatus.LOCKED;
            return true;
        }
        return false;
    }

    public void confirmBooking() {
        if(seatStatus == SeatStatus.LOCKED) {
            seatStatus = SeatStatus.BOOKED;
        }
        else {
            System.out.println("ERROR: Failed to confirm booking");
        }
    }

    public void cancelBooking() {
        seatStatus = SeatStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return String.format("%s", seat.getSeatNo());
    }
}

