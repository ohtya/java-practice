package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.ScreenTime;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.util.ArrayList;
import java.util.List;

import static cinema.ticket.model.VisitorType.COLLEGE;
import static cinema.ticket.model.VisitorType.VOCATIONAL;

/**
 * 大・専門学生
 */
public class CollegeVocationalStudent implements DiscountRule {

    // 大・専門学生の配列
    private static final List<VisitorType> TARGET_VISITOR_TYPE = List.of(COLLEGE, VOCATIONAL);

    @Override
    public boolean isApplicable(Visitor visitor) {
        return TARGET_VISITOR_TYPE.contains(visitor.type());
    }

    /**
     * 適用可能な料金の中から最も安い料金を返します<br>
     * <br>
     * 平日<br>
     * 20:00 までは 1,500 円<br>
     * 20:00 以降は 1,300 円<br>
     * <br>
     * 土日祝日<br>
     * 20:00 までは 1,500 円<br>
     * 20:00 以降は 1,300 円<br>
     * <br>
     * 映画の日(毎月1日)<br>
     * 時間帯によらず 1,100 円<br>
     *
     * @param screenTime {@link ScreenTime}
     * @return 料金
     */
    @Override
    public long price(ScreenTime screenTime) {
        var priceList = new ArrayList<Long>();

        if (screenTime.isMovieDay()) {
            priceList.add(1100L);
        }

        if (screenTime.isLateShow()) {
            priceList.add(1300L);
        }

        return priceList.stream().min(Long::compareTo).orElse(1500L);
    }
}
