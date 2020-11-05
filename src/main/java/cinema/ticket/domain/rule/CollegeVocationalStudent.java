package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;

import java.time.LocalDateTime;

/**
 * 大・専門学生
 */
public class CollegeVocationalStudent implements DiscountRule {

    @Override
    public boolean isApplicable(Visitor visitor) {
        return (visitor.getVisitorType() == VisitorType.COLLEGE ||
            visitor.getVisitorType() == VisitorType.VOCATIONAL);
    }

    @Override
    public long ticketFee() {
        // 現在時刻取得
        final var time = LocalDateTime.now();

        // 映画の日(1日であるか)
        // TODO 何か映画の日のような固定値群があるとよいかもしれない
        if (time.getDayOfMonth() == 1) {
            return 1100;
        }

        // 学生(大・専門)は平日・土日祝値段は同じなため、時間帯のみの判定を行う
        // TODO 祝日考慮(ここでは不要だが別個所で必要)
        //      シネマシティズン(60歳以上)は全部1000円なのでまとめて返却するようにする

        return (time.getHour() < 20) ? 1500 : 1300 ;
    }
}
