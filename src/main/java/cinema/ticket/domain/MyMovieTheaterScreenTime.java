package cinema.ticket.domain;

import lombok.Builder;
import lombok.Value;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 私の映画館の上映日時(架空の映画館)
 */
@Value
@Builder
public class MyMovieTheaterScreenTime implements ScreenTime {

    /**
     * 映画の日<br>
     * 毎月1日を映画の日とする
     */
    private static final List<Integer> MOVIE_DAY = List.of(1);
    /**
     * レイトショーの開始日時
     */
    private static final LocalTime LATE_SHOW_START = LocalTime.of(20, 0);
    /**
     * レイトショーの終了日時(オールナイト営業や深夜営業がない前提)
     */
    private static final LocalTime LATE_SHOW_END = LocalTime.of(23, 59);
    /**
     * 休日<br>
     * FIXME: 祝日
     */
    private static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    // FIXME: 上映時間はフロント側や Controller Layer でバリデーションしている前提
    LocalDateTime screenTime;

    @Override
    public boolean isLateShow() {
        final var time = screenTime.toLocalTime();
        return (LATE_SHOW_START.equals(time) || LATE_SHOW_START.isBefore(time)) &&
                (LATE_SHOW_END.isAfter(time) || LATE_SHOW_END.equals(time));
    }

    @Override
    public boolean isHoliday() {
        return WEEKEND_DAY_OF_WEEK.contains(screenTime.getDayOfWeek());
    }

    @Override
    public boolean isMovieDay() {
        return MOVIE_DAY.contains(screenTime.getDayOfMonth());
    }
}
