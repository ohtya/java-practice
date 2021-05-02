package cinema.ticket.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

/**
 * チケット料金リクエスト
 */
@Value
@Builder
public class TicketPriceRequest implements PriceRequest {

    /**
     * 指定日時
     */
    @NonNull
    LocalDateTime targetDateTime;

    /**
     * {@link Visitor} 一覧
     */
    @NonNull
    List<Visitor> visitorList;

    @Override
    public LocalDateTime targetDateTime() {
        return targetDateTime;
    }

    @Override
    public List<Visitor> visitorList() {
        return visitorList;
    }
}
