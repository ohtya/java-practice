package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 小学生・幼児
 */
public class PrimaryToddler implements DiscountRule {

    protected static final List<VisitorType> TARGET_VISITOR_TYPE = Arrays
            .stream(VisitorType.values())
            .filter(visitorType -> visitorType.equals(VisitorType.PRIMARY_SCHOOL) || visitorType.equals(VisitorType.TODDLER))
            .collect(Collectors.toList());

    @Override
    public boolean isApplicable(Visitor visitor) {
        return TARGET_VISITOR_TYPE.contains(visitor.getVisitorType());
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime) {
        // 小学生・幼児値段は一律
        return 1000;
    }
}
