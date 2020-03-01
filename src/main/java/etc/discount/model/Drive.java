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
    private LocalDateTime admissionAt;
    private LocalDateTime exitAt;
    private CarModel model;
    private Route route;
    private long count;
}
