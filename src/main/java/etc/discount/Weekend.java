package etc.discount;

import java.time.LocalDate;

/**
 * 休日判定
 * <p>
 * 今回、祝日は、考慮しないことにします
 */
public class Weekend {

    /**
     * 休日か？
     *
     * @param date 判定したい日付
     * @return true: 休日, false: 平日
     */
    public boolean isWeekend(final LocalDate date) {
        return true;
    }
}
