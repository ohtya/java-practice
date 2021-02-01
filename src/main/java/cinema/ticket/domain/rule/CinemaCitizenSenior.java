package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * シネマシティズン(シニア)料金算出
 */
public class CinemaCitizenSenior implements DiscountRule {

    protected static final List<DayOfWeek> WEEKEND_DAY_OF_WEEK = Arrays
            .stream(DayOfWeek.values())
            .filter(dayOfWeek -> dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    /**
     * 該当するかどうか
     */
    public boolean isApplicable(final Visitor visitor) {
        return (visitor.isKaiin() && visitor.isSenior());
    }

    /**
     * チケット料金を算出する
     *
     * @return チケット料金
     * @param nowDateTime
     */
    public long discountRate(LocalDateTime nowDateTime) {
        // TODO : 祝日対応
        if (WEEKEND_DAY_OF_WEEK.contains(nowDateTime.getDayOfWeek())) {
            // 土日祝の場合
            if (nowDateTime.getDayOfMonth() == 1){
                // 映画の日
                return 1100;
            } else{
                // レイトショーであるかどうか
                return (nowDateTime.getHour() < 20) ? 1300 : 1000;
            }
        } else {
            // 平日は値段が一律
            return 1000;
        }
    }
}
