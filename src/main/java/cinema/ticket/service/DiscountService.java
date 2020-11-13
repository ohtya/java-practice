package cinema.ticket.service;

import cinema.ticket.model.Visitor;

public interface DiscountService {

    /**
     * チケット料金を決定する
     *
     * @param visitor {@link Visitor}
     * @return チケット料金
     */
    long ticketPriceDecision(final Visitor visitor);
}
