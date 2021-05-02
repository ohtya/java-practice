package cinema.ticket.domain;

import cinema.ticket.model.Visitor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 割引ルール
 *
 * - 学生や一般といった区分で割引する
 * -
 */
public interface DiscountRule {

    static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    /**
     * 適用可能か？
     *
     * @return true: 可能, false: 不可
     */
    boolean isApplicable(final Visitor visitor);

    /**
     * チケット料金返却
     *
     * @return チケット料金
     * @param nowDateTime
     */
    long discountRate(final LocalDateTime nowDateTime,Visitor visitor);
}
