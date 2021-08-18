package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.ScreenTime;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.util.ArrayList;

/**
 * 割引ルール
 * 一般
 */
public class General implements DiscountRule {

    @Override
    public boolean isApplicable(final Visitor visitor) {
        return visitor.type() == VisitorType.GENERAL;
    }

    @Override
    public long price(ScreenTime screenTime) {
        var priceList = new ArrayList<Long>();

        if (screenTime.isMovieDay()) {
            priceList.add(1100L);
        }

        if (screenTime.isLateShow()) {
            priceList.add(1300L);
        }

        return priceList.stream().min(Long::compareTo).orElse(1800L);
    }
}
