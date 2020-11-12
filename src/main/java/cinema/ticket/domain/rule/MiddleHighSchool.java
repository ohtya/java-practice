package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

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
    public long ticketFee() {
        // 中高生の値段は一律
        return 1000;
    }
}
