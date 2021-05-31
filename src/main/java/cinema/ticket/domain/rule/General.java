package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // 一般・シニア
    protected static final List<VisitorType> TARGET_VISITOR_TYPE = Arrays
            .stream(VisitorType.values())
            .filter(visitorType -> visitorType.equals(VisitorType.GENERAL))
            .collect(Collectors.toList());
    /**
     * 適応可能であるかどうか
     *
     * @return
     */
    public boolean isApplicable(final Visitor visitor) {
        return TARGET_VISITOR_TYPE.contains(visitor.getVisitorType());
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        var priceList = new ArrayList<Long>();

        // 一般の値段決定
        // 映画の日
        if (nowDateTime.getDayOfMonth() == 1) {
            priceList.add(1100L);
        }

        // 休日であるか
        if (nowDateTime.getHour() < 20) {
            priceList.add(1800L);
        }

        return priceList.stream().min(Long::compareTo).orElse(1300L);
    }
}
