package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

/**
 * シネマシティズン料金算出
 */
public class CinemaCitizen implements DiscountRule {

    /**
     * 該当するかどうか
     */
    public boolean isApplicable(final Visitor visitor) {
        return (visitor.isKaiin() && !visitor.isSenior());
    }

    /**
     * チケット料金を算出する
     *
     * @return チケット料金
     * @param nowDateTime
     */
    @Override
    public long discountRate(LocalDateTime nowDateTime,Visitor visitor) {
        // 映画の日
        if (nowDateTime.getDayOfMonth() == 1) {
            return 1100;
        }

        // TODO : 祝日対応
        // 土日祝の場合
        if (WEEKEND_DAY_OF_WEEK.contains(nowDateTime.getDayOfWeek())) {
            // レイトショーであるかどうか
            return (nowDateTime.getHour() < 20) ? 1300 : 1000;
        } else {
            // 平日の場合(現状値段は同じ)
            return (nowDateTime.getHour() < 20) ? 1000 : 1000;
        }
    }
}
