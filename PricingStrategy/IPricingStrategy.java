package Practice.MovieBookingSystem.PricingStrategy;

import Practice.MovieBookingSystem.DTOs.Show;

public interface IPricingStrategy {
    boolean isApplicable(Show show);
    double apply(Double currentPrice);
}
