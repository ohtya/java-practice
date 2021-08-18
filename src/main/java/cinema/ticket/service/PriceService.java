package cinema.ticket.service;

import cinema.ticket.model.PriceRequest;
import cinema.ticket.model.PriceResponse;

/**
 * 料金サービス
 */
public interface PriceService {

    /**
     * 料金を返します
     *
     * @param priceRequest {@link PriceRequest}
     * @return {@link PriceResponse}
     */
    PriceResponse price(final PriceRequest priceRequest);
}
