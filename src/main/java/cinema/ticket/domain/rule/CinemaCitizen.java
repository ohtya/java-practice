package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

/**
 * シネマシティズン料金算出<br>
 * シネマシティズン会員の場合に適用する料金です<br>
 * 適用可能な料金の中から最も安い料金を返します<br>
 * なお、シニアの場合は {@link CinemaCitizenSenior} を適用します<br>
 * <br>
 * 平日<br>
 * 20:00 までは 1,000 円<br>
 * 20:00 以降は 1,000 円<br>
 * <br>
 * 土日祝日<br>
 * 20:00 までは 1,300 円<br>
 * 20:00 以降は 1,000 円<br>
 * <br>
 * 映画の日(毎月1日)<br>
 * 時間帯によらず 1,100 円<br>
 */
public class CinemaCitizen implements DiscountRule {

    public boolean isApplicable(final Visitor visitor) {
        return (visitor.isKaiin() && !visitor.isSenior());
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
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
