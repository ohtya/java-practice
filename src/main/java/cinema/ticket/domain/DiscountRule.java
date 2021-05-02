package cinema.ticket.domain;

import cinema.ticket.model.Visitor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 割引ルール
 */
public interface DiscountRule {

    List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    /**
     * 割引を適用可能か？
     *
     * @return true: 可能, false: 不可
     */
    boolean isApplicable(final Visitor visitor);

    /**
     * チケット料金を算出します
     *
     * @param nowDateTime 現在日時
     * @return チケット料金
     */
    long discountRate(final LocalDateTime nowDateTime, final Visitor visitor);
}
