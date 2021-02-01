package cinema.ticket.domain.dateAndTime;

import cinema.ticket.domain.DateAndTimeRule;
import cinema.ticket.domain.DiscountRule;

public class WeekdayLate implements DateAndTimeRule {
    @Override
    public long getRate(DiscountRule rule) {
        return rule.getWeekdayLateDiscount();
    }
}
