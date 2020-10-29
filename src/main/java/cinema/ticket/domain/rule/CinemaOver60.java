package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import etc.discount.model.Visitor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CinemaOver60 implements DiscountRule {

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    /**
     * 該当するかどうか
     * TODO Visitorの種類をどう持つか考える
     *      年齢の比較に対するConstなどをどうするか考える
     */
    public boolean isApplicable(final Visitor visitor) {

        // Visitorの種別を判別する
        return (visitor.getVisitorType() == 0 /* シネマシティズン60以上 */
                && visitor.getAge() >= 60);
    }

    /**
     * チケット料金を算出する
     */
    public long ticketFee() {

        // 現在時刻より色々と判定する
        final var time = LocalDateTime.now();

        // 映画の日(1日)であるか
        // TODO 直値をなおす
        if (time.getDayOfMonth() == 1) {
            return 1000;
        }

        // 平日か土日祝か TODO 祝日
        // 休日
        if (WEEKEND_DAY_OF_WEEK.contains(time.getDayOfWeek())) {
            return (time.getHour() < 20) ? 1000 : 1000;
        }
        else {
            // 平日
            return (time.getHour() < 20) ? 1000 : 1000;
        }
    }
}
