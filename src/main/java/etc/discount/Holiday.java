package etc.discount;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 休日判定
 * <p>
 * 今回、祝日は、考慮しないことにします
 */
public class Holiday {

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
        .stream(DayOfWeek.values())
        .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
        .collect(Collectors.toList());

    /**
     * 休日か？
     *
     * @param date 判定したい日付
     * @return true: 休日, false: 平日
     */
    public boolean isWeekend(final LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("arg1 can not be null.");
        }

        return WEEKEND_DAY_OF_WEEK.contains(date.getDayOfWeek());
    }
}
