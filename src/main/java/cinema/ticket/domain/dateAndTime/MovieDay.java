package cinema.ticket.domain.dateAndTime;

import cinema.ticket.domain.DateAndTimeRule;
import cinema.ticket.domain.DiscountRule;

public class MovieDay implements DateAndTimeRule {
    @Override
    public long getRate(DiscountRule rule) {
        return rule.getMovieDayDiscount();
    }
}
