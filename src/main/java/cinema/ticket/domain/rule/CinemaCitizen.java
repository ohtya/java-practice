package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * シネマシティズン料金算出
 */
public class CinemaCitizen implements DiscountRule {

    // 60歳以上であるか
    private boolean is_senior;

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    /**
     * 該当するかどうか
     */
    public boolean isApplicable(final Visitor visitor) {
        // シニアは割引が変わる
        is_senior = (60 <= visitor.getAge());
        return (visitor.isKaiin());
    }

    /**
     * チケット料金を算出する
     *
     * @return チケット料金
     */
    public long ticketFee() {
        // 会員かつシニアは値段が一律
        if (is_senior) {
            return 1000;
        }

        // シニアでない場合は現在時刻をもとに料金を決定する
        final var time = LocalDateTime.now();

        // TODO : 祝日対応
        if (WEEKEND_DAY_OF_WEEK.contains(time.getDayOfWeek())) {
            // 土日祝の場合
            if (time.getDayOfMonth() == 1){
                // 映画の日
                return 1100;
            } else{
                // レイトショーであるかどうか
                return (time.getHour() < 20) ? 1300 : 1000;
            }
        } else {
            // 平日は値段が一律
            return 1000;
        }
    }
}
