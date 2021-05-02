package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 中・高校生
 */
public class MiddleHighSchool implements DiscountRule {

    // 中・高校生の配列
    protected static final List<VisitorType> TARGET_VISITOR_TYPE = Arrays
            .stream(VisitorType.values())
            .filter(visitorType -> visitorType.equals(VisitorType.JUNIOR_HIGH_SCHOOL) || visitorType.equals(VisitorType.HIGH_SCHOOL))
            .collect(Collectors.toList());

    @Override
    public boolean isApplicable(Visitor visitor) {
        return (TARGET_VISITOR_TYPE.contains(visitor.getVisitorType()));
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime,Visitor visitor) {
        // 中高生の値段は一律
        // 映画の日
        if (nowDateTime.getDayOfMonth() == 1) {
            return 1000;
        }

        // 休日であるか
        if (WEEKEND_DAY_OF_WEEK.contains(nowDateTime.getDayOfWeek())) {
            // レイトショーであるかどうか
            return (nowDateTime.getHour() < 20) ? 1000 : 1000;
        } else {
            // 平日の場合
            return (nowDateTime.getHour() < 20) ? 1000 : 1000;
        }
    }
}
