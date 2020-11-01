package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
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

        // 現在時刻より色々と判定する
        final var time = LocalDateTime.now();

        // 映画の日(1日)であるか
        // TODO 何か映画の日のような固定値群があるとよいかもしれない
        if (time.getDayOfMonth() == 1) {
            return 1000;
        }

        // 平日か土日祝か
        // TODO 祝日考慮
        //      シネマシティズン(60歳以上)は全部1000円なのでまとめて返却するようにする
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
