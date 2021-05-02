package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Handicapped implements DiscountRule {

    // 中・高校生の配列
    protected static final List<VisitorType> TARGET_VISITOR_TYPE = Arrays
            .stream(VisitorType.values())
            .filter(visitorType -> visitorType.equals(VisitorType.JUNIOR_HIGH_SCHOOL) || visitorType.equals(VisitorType.HIGH_SCHOOL))
            .collect(Collectors.toList());

    @Override
    public boolean isApplicable(Visitor visitor) {
        return visitor.isSyougaisya();
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        return 1000L;
    }
}
