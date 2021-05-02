package cinema.ticket.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 料金リクエスト
 */
public interface PriceRequest {

    /**
     * 指定日時
     */
    LocalDateTime targetDateTime();

    /**
     * {@link Visitor} 一覧を返します
     *
     * @return {@link Visitor} 一覧
     */
    List<Visitor> visitorList();
}
