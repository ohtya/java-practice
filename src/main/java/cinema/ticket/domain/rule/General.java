package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.ScreenTime;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 割引ルール
 * 一般
 *
 *
 */
public class General implements DiscountRule {

    /**
     * 適応可能であるかどうか
     *
     * @return
     */
    @Override
    public boolean isApplicable(final Visitor visitor) {
        return visitor.type() == VisitorType.GENERAL;
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        var priceList = new ArrayList<Long>();

        if (nowDateTime.getDayOfMonth() == 1) {
            priceList.add(1100L);
        }

        if (nowDateTime.getHour() < 20) {
            priceList.add(1800L);
        }
        return priceList.stream().min(Long::compareTo).orElse(1300L);
    }

    @Override
    public long price(ScreenTime screenTime) {
        return 0;
    }
}
