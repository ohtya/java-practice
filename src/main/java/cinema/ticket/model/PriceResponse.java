package cinema.ticket.model;

/**
 * 料金レスポンス
 */
public interface PriceResponse {

    /**
     * 総料金を返します
     *
     * @return 総料金
     */
    Long totalPrice();
}
