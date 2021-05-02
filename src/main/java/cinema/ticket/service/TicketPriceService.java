package cinema.ticket.service;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.rule.*;
import cinema.ticket.model.PriceRequest;
import cinema.ticket.model.PriceResponse;
import cinema.ticket.model.TicketPriceResponse;
import cinema.ticket.model.Visitor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * チケットの料金を決定するサービス
 */
public class TicketPriceService implements PriceService {

    // TODO：現状の実装だと小・中学生などでなく一個一個クラスを分けた方が拡張性があるのかもしれない
    private final List<DiscountRule> discountRuleList = List.of(
            // FIXME: Baby
            new PrimaryToddler(),
            new MiddleHighSchool(),
            // FIXME: Handicapped
            new CinemaCitizenSenior(),
            new Senior(),
            new CinemaCitizen(),
            new CollegeVocationalStudent(),
            new General());

    @Override
    public long price(Visitor visitor) {
        // Minの使い方がわからなくてめっちゃここで悩んだ(現状動くかもわからない)
        // ググり力と応用力が足りないと同時に基礎をちゃんと理解する必要がある。
        // 現にComparator.naturalOrder()が何なのかちゃんと調べないとわからない。
        return discountRuleList
                .stream()
                .filter(rule -> rule.isApplicable(visitor))
                .map(rule -> rule.discountRate(LocalDateTime.now(), visitor))
                .min(Comparator.naturalOrder())
                .get();
    }

    @Override
    public PriceResponse price(final PriceRequest priceRequest) {
        // 親の同伴が必須、などのガードは呼び出し元で行われている前提とする

        final var totalPrice = priceRequest.visitorList().stream().mapToLong(
                visitor -> price(priceRequest.targetDateTime(), visitor)
        ).sum();

        // FIXME: 消費税の計算はどこでやるのがベターか
        return TicketPriceResponse.builder()
                .totalPrice(totalPrice)
                .build();
    }

    private Long price(final LocalDateTime targetDateTime, final Visitor visitor) {

        return discountRuleList
                .stream()
                .filter(rule -> rule.isApplicable(visitor))
                .map(rule -> rule.discountRate(targetDateTime, visitor))
                .min(Comparator.naturalOrder())
                .get();
    }
}
