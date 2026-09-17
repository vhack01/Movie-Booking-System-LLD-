package Practice.MovieBookingSystem.PricingStrategy;

import Practice.MovieBookingSystem.DTOs.Show;

import java.time.DayOfWeek;

public class WeekendPrice implements IPricingStrategy {
    private final double percentageOff;

    public WeekendPrice(double percentageOff) {
        this.percentageOff = percentageOff;
    }

    @Override
    public boolean isApplicable(Show show) {
        DayOfWeek day = show.getStartTime().getDayOfWeek();
        return day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY);
    }

    @Override
    public double apply(Double currentPrice) {
        return currentPrice - ((percentageOff * currentPrice) / 100);
    }
}
