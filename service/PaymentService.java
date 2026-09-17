package Practice.MovieBookingSystem.service;

import Practice.MovieBookingSystem.DTOs.Booking;
import Practice.MovieBookingSystem.DTOs.BookingStatus;

public class PaymentService {

    public BookingStatus proceedToPayment(Booking booking) {
        try {
            Thread.sleep(10000);
            return BookingStatus.SUCCESS;
        }
        catch (InterruptedException e) {
            return BookingStatus.FAILED;
        }
    }

    public boolean refund(Booking booking) {
        try {
            Thread.sleep(30000);
            return true;
        }
        catch (InterruptedException e) {
            return false;
        }
    }

}
