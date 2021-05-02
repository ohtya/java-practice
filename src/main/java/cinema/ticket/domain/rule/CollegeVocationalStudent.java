package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 大・専門学生
 */
public class CollegeVocationalStudent implements DiscountRule {

    // 大・専門学生の配列
    protected static final List<VisitorType> TARGET_VISITOR_TYPE = Arrays
            .stream(VisitorType.values())
            .filter(visitorType -> visitorType.equals(VisitorType.COLLEGE) || visitorType.equals(VisitorType.VOCATIONAL))
            .collect(Collectors.toList());

    @Override
    public boolean isApplicable(Visitor visitor) {
        return TARGET_VISITOR_TYPE.contains(visitor.getVisitorType());
    }

    @Override
    public long discountRate(LocalDateTime nowDateTime, Visitor visitor) {

        // 映画の日(1日であるか)
        // TODO 何か映画の日のような固定値群があるとよいかもしれない
        if (nowDateTime.getDayOfMonth() == 1) {
            return 1100;
        }

        // 学生(大・専門)は平日・土日祝値段は同じなため、時間帯のみの判定を行う
        // TODO 祝日考慮(ここでは不要だが別個所で必要)
        //      シネマシティズン(60歳以上)は全部1000円なのでまとめて返却するようにする
        if (WEEKEND_DAY_OF_WEEK.contains(nowDateTime.getDayOfWeek())) {
            // レイトショーであるかどうか
            return (nowDateTime.getHour() < 20) ? 1500 : 1300;
        } else {
            // 平日の場合(現状値段は同じ)
            return (nowDateTime.getHour() < 20) ? 1500 : 1300;
        }
    }
}
