package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 割引ルール
 * 一般
 *
 *
 */
public class General implements DiscountRule {

    // 70歳以上であるか
    private boolean is_senior;

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    // 一般・シニア
    protected static final List<VisitorType> TARGET_VISITOR_TYPE = Arrays
            .stream(VisitorType.values())
            .filter(visitorType -> visitorType.equals(VisitorType.GENERAL) || visitorType.equals(VisitorType.SENIOR))
            .collect(Collectors.toList());
    /**
     * 適応可能であるかどうか
     *
     * @return
     */
    public boolean isApplicable(final Visitor visitor) {
        is_senior = (70 <= visitor.getAge());
        return TARGET_VISITOR_TYPE.contains(visitor.getVisitorType());
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime) {
        // シニアは値段は一律
        if (is_senior) {
            return 1100;
        }

        // 一般の値段決定
        final var time = LocalDateTime.now();
        // 映画の日
        if (time.getDayOfMonth() == 1) {
            return 1100;
        }
        // 一般はレイトかそうでないかのみで金額に差分が生じる
        return (time.getHour() < 20) ? 1800 : 1300;
    }
}
