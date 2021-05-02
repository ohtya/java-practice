package cinema.ticket.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * チケット料金レスポンス
 */
@Value
@Builder
public class TicketPriceResponse implements PriceResponse {

    /**
     * 総料金
     */
    @NonNull
    Long totalPrice;

    @Override
    public Long totalPrice() {
        return totalPrice;
    }
}
