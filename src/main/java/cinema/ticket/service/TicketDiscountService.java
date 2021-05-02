package cinema.ticket.service;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.rule.*;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * チケットの料金を決定するサービス
 */
public class TicketDiscountService implements DiscountService{

    // TODO：現状の実装だと小・中学生などでなく一個一個クラスを分けた方が拡張性があるのかもしれない
    private final List<DiscountRule> ticketDiscountRules = List.of(
            new CinemaCitizen(), new CollegeVocationalStudent(), new General(),
            new MiddleHighSchool(), new PrimaryToddler());

    @Override
    public long ticketPriceDecision(Visitor visitor) {
        // Minの使い方がわからなくてめっちゃここで悩んだ(現状動くかもわからない)
        // ググり力と応用力が足りないと同時に基礎をちゃんと理解する必要がある。
        // 現にComparator.naturalOrder()が何なのかちゃんと調べないとわからない。
        return ticketDiscountRules
                .stream()
                .filter(rule -> rule.isApplicable(visitor))
                .map(rule -> rule.discountRate(LocalDateTime.now(),visitor))
                .min(Comparator.naturalOrder())
                .get();
    }
}
