package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

import static cinema.ticket.model.VisitorType.CINEMA_CITIZEN_SENIOR;

/**
 * シネマシティズン(シニア)料金算出
 */
public class CinemaCitizenSenior implements DiscountRule {

    @Override
    public boolean isApplicable(final Visitor visitor) {
        return visitor.type() == CINEMA_CITIZEN_SENIOR;
    }

    @Override
    public long discountRate(final LocalDateTime nowDateTime, final Visitor visitor) {
        return 1000L;
    }
}
