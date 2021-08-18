package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.ScreenTime;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

/**
 * 割引ルール
 * シニア
 */
public class Senior implements DiscountRule {

    @Override
    public boolean isApplicable(Visitor visitor) {
        return visitor.type() == VisitorType.SENIOR;
    }

    @Override
    public long price(ScreenTime screenTime) {
        return 1100L;
    }
}
