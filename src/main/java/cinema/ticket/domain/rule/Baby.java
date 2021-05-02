package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

import static cinema.ticket.model.VisitorType.BABY;

/**
 * 乳児料金<br>
 * 仕様上、存在しない条件に合致した場合にこのクラスの処理を行います<br>
 * いわゆる null オブジェクトです
 */
public class Baby implements DiscountRule {

    @Override
    public boolean isApplicable(final Visitor visitor) {
        return visitor.type() == BABY;
    }

    @Override
    public long discountRate(final LocalDateTime nowDateTime, final Visitor visitor) {
        return 0L;
    }
}
