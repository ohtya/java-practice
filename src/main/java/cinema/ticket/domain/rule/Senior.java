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
        return visitor.type() == VisitorType.SENIOR;
    }

    /**
     * 料金を返却する
     *
     * @param nowDateTime 現在日時
     * @param visitor {@link Visitor}
     * @return 料金
     */
    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        return 1100L;
    }
}
