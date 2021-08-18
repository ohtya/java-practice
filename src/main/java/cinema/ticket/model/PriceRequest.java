package cinema.ticket.model;

import cinema.ticket.domain.ScreenTime;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 料金リクエスト
 */
public interface PriceRequest {


    ScreenTime screenTime();

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
