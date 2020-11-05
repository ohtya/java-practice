package cinema.ticket.domain;

import cinema.ticket.model.Visitor;

/**
 * 割引ルール
 *
 * - 学生や一般といった区分で割引する
 * -
 */
public interface DiscountRule {

    /**
     * 適用可能か？
     *
     * @return true: 可能, false: 不可
     */
    boolean isApplicable(final Visitor visitor);

    /**
     * チケット料金返却
     *
     * @return チケット料金
     */
    long ticketFee();

}
