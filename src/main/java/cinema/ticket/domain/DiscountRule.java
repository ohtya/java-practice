package cinema.ticket.domain;

import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

/**
 * 割引ルール
 *
 * - 学生や一般といった区分で割引する
 * -
 */
public interface DiscountRule {

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
    long discountRate(final LocalDateTime nowDateTime);

    // 平日の割引額
    long getWeekdayDiscount();

    // 平日レイトの割引額
    long getWeekdayLateDiscount();

    // 休日の割引額
    long getHolidayDiscount();

    // 休日レイトの割引額
    long getHolidayLateDiscount();

    // 映画の日の割引額
    long getMovieDayDiscount();

}
