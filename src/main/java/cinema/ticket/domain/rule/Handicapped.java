package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

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
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        return 1000L;
    }
}
