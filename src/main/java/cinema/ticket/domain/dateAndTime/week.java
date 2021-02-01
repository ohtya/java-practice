package cinema.ticket.domain.dateAndTime;

import cinema.ticket.domain.DateAndTimeRule;
import cinema.ticket.domain.DiscountRule;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class week {

    private static final LocalTime LATE_START_AT = LocalTime.of(20, 0);

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList()
            );

    public DateAndTimeRule hoge(DiscountRule rule, final LocalDateTime nowDate) {
        // 映画の日
        if (nowDate.getDayOfMonth() == 1) {
            return new MovieDay();
        }

        if (WEEKEND_DAY_OF_WEEK.contains(nowDate.getDayOfWeek())) {
            if (LATE_START_AT.isAfter(LocalTime.of(nowDate.getHour(), nowDate.getMinute()))) {
                // 休日レイト
                return new HolidayLate();
            } else {
                // 休日
                return new Holiday();
            }
        } else {
            if (LATE_START_AT.isAfter(LocalTime.of(nowDate.getHour(), nowDate.getMinute()))) {
                // 平日レイト
                return new WeekdayLate();
            } else {
                // 平日
                return new Weekday();
            }
        }
    }
}
