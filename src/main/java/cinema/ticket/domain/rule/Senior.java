package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;

/**
 * 割引ルール
 * シニア
 *
 *
 */
public class Senior implements DiscountRule {
    @Override
    public boolean isApplicable(Visitor visitor) {
        return visitor.getVisitorType() == VisitorType.SENIOR;
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        // 映画の日
        if (nowDateTime.getDayOfMonth() == 1) {
            return 1100;
        }

        // 休日であるか
        if (WEEKEND_DAY_OF_WEEK.contains(nowDateTime.getDayOfWeek())) {
            // レイトショーであるかどうか
            return (nowDateTime.getHour() < 20) ? 1100 : 1100;
        } else {
            // 平日の場合
            return (nowDateTime.getHour() < 20) ? 1100 : 1100;
        }
    }
}
