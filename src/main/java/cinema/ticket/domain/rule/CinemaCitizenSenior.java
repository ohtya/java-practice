package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

/**
 * シネマシティズン(シニア)料金算出
 */
public class CinemaCitizenSenior implements DiscountRule {

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
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        if (nowDateTime.getDayOfMonth() == 1){
            // 映画の日
            return 1000;
        }
        // TODO : 祝日対応
        if (WEEKEND_DAY_OF_WEEK.contains(nowDateTime.getDayOfWeek())) {
            // 土日祝の場合
            // レイトショーであるかどうか
            return (nowDateTime.getHour() < 20) ? 1000 : 1000;
        } else {
            // 平日は値段が一律
            return (nowDateTime.getHour() < 20) ? 1000 : 1000;
        }
    }
}
