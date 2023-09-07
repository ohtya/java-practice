package cinema.ticket.domain;

import cinema.ticket.model.Visitor;

/**
 * 割引ルールインタフェース
 */
public interface DiscountRule {

    /**
     * 割引を適用可能か？
     *
     * @return true: 可能, false: 不可
     */
    boolean isApplicable(final Visitor visitor);

    /**
     * チケット料金を算出します
     *
     * @param screenTime {@link ScreenTime}
     * @return チケット料金
     */
    long price(final ScreenTime screenTime);
}
