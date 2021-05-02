package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
        return visitor.isMember() && !visitor.isSenior();
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
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
