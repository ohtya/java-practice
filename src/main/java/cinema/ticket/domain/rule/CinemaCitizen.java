package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static cinema.ticket.model.VisitorType.CINEMA_CITIZEN;

/**
 * シネマシティズン料金<br>
 * シネマシティズン会員の場合に適用する料金ルールです<br>
 * なお、シニアの場合は {@link CinemaCitizenSenior} を適用します<br>
 */
public class CinemaCitizen implements DiscountRule {

    @Override
    public boolean isApplicable(final Visitor visitor) {
        return visitor.type() == CINEMA_CITIZEN;
    }

    /**
     * 適用可能な料金の中から最も安い料金を返します<br>
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
     *
     * @param nowDateTime 現在日時
     * @param visitor     {@link Visitor}
     * @return 料金
     */
    @Override
    public long discountRate(final LocalDateTime nowDateTime, final Visitor visitor) {
        var priceList = new ArrayList<Long>();

        if (nowDateTime.getDayOfMonth() == 1) {
            priceList.add(1100L);
        }

        // FIXME: 祝日対応
        if (WEEKEND_DAY_OF_WEEK.contains(nowDateTime.getDayOfWeek())) {
            if (20 <= nowDateTime.getHour()) {
                priceList.add(1000L);
            }
        } else {
            priceList.add(1000L);
        }

        return priceList.stream().min(Long::compareTo).orElse(1300L);
    }
}
