package Practice.MovieBookingSystem.exceptions;

public class SeatNoAvailableException extends RuntimeException{

    public SeatNoAvailableException(String message) {
        super(message);
    }
}
