package Practice.MovieBookingSystem.utils;

import Practice.MovieBookingSystem.DTOs.HolidayCalendar;
import Practice.MovieBookingSystem.PricingStrategy.HolidayPrice;
import Practice.MovieBookingSystem.PricingStrategy.IPricingStrategy;
import Practice.MovieBookingSystem.PricingStrategy.WeekendPrice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PricingUtils {
    public static List<IPricingStrategy> getAllPricingStrategy() {
        List<IPricingStrategy> strategies = new ArrayList<>();
        strategies.add(new WeekendPrice(10.0));
        strategies.add(new HolidayPrice(15.0, new HolidayCalendar(getHolidays())));
        return strategies;
    }

    private static Set<LocalDate> getHolidays() {
        Set<LocalDate> holidays = new HashSet<>();
        holidays.add(LocalDate.of(2026, 3, 2));
        holidays.add(LocalDate.of(2026, 5, 8));
        holidays.add(LocalDate.of(2026, 6, 19));
        holidays.add(LocalDate.of(2026, 8, 2));
        holidays.add(LocalDate.of(2026, 11, 9));
        holidays.add(LocalDate.of(2026, 12, 25));
        return holidays;
    }
}
