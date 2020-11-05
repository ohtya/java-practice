package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * シネマシティズン(60以上)料金算出
 */
public class CinemaOver60 implements DiscountRule {

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    /**
     * 該当するかどうか
     * TODO 年齢の比較に対するConstなどをどうするか考える
     */
    public boolean isApplicable(final Visitor visitor) {
        return (visitor.isKaiin() &&
                60 <= visitor.getAge() );
    }

    /**
     * チケット料金を算出する
     *
     * @return チケット料金
     */
    public long ticketFee() {
        // シネマシティズン(60以上)のチケット料金は1000円固定
        return 1000;
    }
}
