package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.ScreenTime;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;
import java.util.List;

import static cinema.ticket.model.VisitorType.PRIMARY_SCHOOL;
import static cinema.ticket.model.VisitorType.TODDLER;

/**
 * 幼児・小学生料金<br>
 * 幼児、または、小学生の場合に適用する料金ルールです<br>
 */
public class PrimaryToddler implements DiscountRule {

    protected static final List<VisitorType> TARGET_VISITOR_TYPE = List.of(PRIMARY_SCHOOL, TODDLER);

    @Override
    public boolean isApplicable(Visitor visitor) {
        return TARGET_VISITOR_TYPE.contains(visitor.type());
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
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {
        return 1000L;
    }

    @Override
    public long price(ScreenTime screenTime) {
        return 0;
    }
}
