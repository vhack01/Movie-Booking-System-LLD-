package Practice.MovieBookingSystem.DTOs;

import java.time.LocalDate;
import java.util.Set;

public class HolidayCalendar {
    private final Set<LocalDate> holidays;

    public HolidayCalendar(Set<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
