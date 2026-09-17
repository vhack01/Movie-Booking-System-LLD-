package Practice.MovieBookingSystem.PricingStrategy;

import Practice.MovieBookingSystem.DTOs.HolidayCalendar;
import Practice.MovieBookingSystem.DTOs.Show;

public class HolidayPrice implements IPricingStrategy {
    private final double percentageOff;
    private final HolidayCalendar holidayCalendar;

    public HolidayPrice(double percentageOff, HolidayCalendar holidayCalendar) {
        this.percentageOff = percentageOff;
        this.holidayCalendar = holidayCalendar;
    }

    @Override
    public boolean isApplicable(Show show) {
        return holidayCalendar.isHoliday(show.getStartTime().toLocalDate());
    }

    @Override
    public double apply(Double currentPrice) {
        return currentPrice - (percentageOff * currentPrice) / 100;
    }
}
