package etc.discount.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 走行データ
 */
@Builder
@Value
public class Drive {
    /**
     * 料金所に入った時間
     */
    LocalDateTime admissionAt;
    /**
     * 料金所から出た時間
     */
    LocalDateTime exitAt;
    /**
     * {@link CarModel}
     */
    CarModel model;
    /**
     * {@link Route}
     */
    Route route;
    /**
     * 1か月の走行回数
     */
    long count;
}
