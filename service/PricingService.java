package Practice.MovieBookingSystem.service;

import Practice.MovieBookingSystem.DTOs.Show;
import Practice.MovieBookingSystem.DTOs.ShowSeat;
import Practice.MovieBookingSystem.PricingStrategy.IPricingStrategy;

import java.util.List;

public class PricingService {
    private final List<IPricingStrategy> allStrategies;

    public PricingService(List<IPricingStrategy> allStrategies) {
        this.allStrategies = allStrategies;
    }

    public double calculateBookingAmount(Show show, List<ShowSeat> showSeatList) {
        double totalAmount = 0.0;

        for(ShowSeat showSeat : showSeatList) {
            double price = showSeat.getPrice();
            for(IPricingStrategy strategy : allStrategies) {
                if(strategy.isApplicable(show)) {
                    price = strategy.apply(price);
                }
            }
            totalAmount += price;
        }
        return totalAmount;
    }
}
