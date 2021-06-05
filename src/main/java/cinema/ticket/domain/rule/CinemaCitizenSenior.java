package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.ScreenTime;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;

import static cinema.ticket.model.VisitorType.CINEMA_CITIZEN_SENIOR;

/**
 * シネマシティズン(シニア)料金<br>
 * シネマシティズン(シニア)会員の場合に適用する料金ルールです<br>
 * なお、シニアではない場合は {@link CinemaCitizen} を適用します<br>
 */
public class CinemaCitizenSenior implements DiscountRule {

    @Override
    public boolean isApplicable(final Visitor visitor) {
        return visitor.type() == CINEMA_CITIZEN_SENIOR;
    }

    /**
     * 適用可能な料金の中から最も安い料金を返します<br>
     * <br>
     * 平日<br>
     * 20:00 までは 1,000 円<br>
     * 20:00 以降は 1,000 円<br>
     * <br>
     * 土日祝日<br>
     * 20:00 までは 1,000 円<br>
     * 20:00 以降は 1,000 円<br>
     * <br>
     * 映画の日(毎月1日)<br>
     * 時間帯によらず 1,000 円<br>
     *
     * @param nowDateTime 現在日時
     * @param visitor     {@link Visitor}
     * @return 料金
     */
    @Override
    public long discountRate(final LocalDateTime nowDateTime, final Visitor visitor) {
        return 1000L;
    }

    @Override
    public long price(ScreenTime screenTime) {
        return 0;
    }
}
