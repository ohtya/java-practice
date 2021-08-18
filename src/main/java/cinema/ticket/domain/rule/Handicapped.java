package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.ScreenTime;
import cinema.ticket.model.Visitor;

import static cinema.ticket.model.VisitorType.HANDICAPPED;

/**
 * 障がい者(学生以上)料金<br>
 * 障がい者(学生以上)の場合に適用する料金ルールです<br>
 */
public class Handicapped implements DiscountRule {

    @Override
    public boolean isApplicable(Visitor visitor) {
        return visitor.type() == HANDICAPPED;
    }

    @Override
    public long price(ScreenTime screenTime) {
        return 1000L;
    }
}
