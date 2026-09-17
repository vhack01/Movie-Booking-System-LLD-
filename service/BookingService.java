package Practice.MovieBookingSystem.service;

import Practice.MovieBookingSystem.DTOs.*;
import Practice.MovieBookingSystem.utils.BaseUtil;
import Practice.MovieBookingSystem.utils.PricingUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BookingService {

    private final MovieSearchService movieSearchService;
    private final PricingService pricingService;
    private final PaymentService paymentService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
    private Map<String, City> cityMap;

    public BookingService() {
        movieSearchService = new MovieSearchService();
        pricingService = new PricingService(PricingUtils.getAllPricingStrategy());
        paymentService = new PaymentService();

        initialize();
    }

    private void initialize() {
        cityMap = BaseUtil.getCities();
    }

    public City getCity(String cityName) {
        return cityMap.getOrDefault(cityName, null);
    }

    public List<Show> getShows(City city, String movieTitle) {
        return movieSearchService.findShow(city, movieTitle);
    }

    public Ticket createBooking(Show show, List<String> selectedSeats) {
        if(selectedSeats.isEmpty() || show == null) {
            throw new IllegalArgumentException("Invalid booking request");
        }

        // Step 1: lock - throws if any seat is invalid or unavailable (all or nothing)
        List<ShowSeat> lockedShowSeats = show.lockSeat(selectedSeats);

        // Step 2: safety-net timer, in case payment never resolves at all (user left the flow)
        ScheduledFuture<?> releaseTask = scheduler.schedule(()-> show.releaseSeats(lockedShowSeats), 5, TimeUnit.MINUTES);

        Booking booking = new Booking();
        booking.setShow(show);
        booking.setBookedSeat(lockedShowSeats);

        try {
            double totalAmount = pricingService.calculateBookingAmount(show, lockedShowSeats);
            booking.setAmount(totalAmount);

            BookingStatus bookingStatus = paymentService.proceedToPayment(booking);

            if(bookingStatus == BookingStatus.SUCCESS) {
                boolean isConfirmed = show.confirmSeats(lockedShowSeats);
                if(!isConfirmed) {
                    booking.setBookingStatus(BookingStatus.FAILED);
                    paymentService.refund(booking);
                    return null;
                }
                booking.setBookingStatus(BookingStatus.SUCCESS);
                return createTicket(booking);
            }
            else {
                show.releaseSeats(lockedShowSeats);
                booking.setBookingStatus(BookingStatus.FAILED);
                return null;
            }
        } catch (Exception e) {
            show.releaseSeats(lockedShowSeats);
            throw new RuntimeException(e);
        }

        finally {
            releaseTask.cancel(false);
        }
    }

    private Ticket createTicket(Booking booking) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(UUID.randomUUID());
        ticket.setBooking(booking);
        ticket.setQR("ADFADSF");
        return ticket;
    }

    private void shutdown() {
        scheduler.shutdown();
    }
}
