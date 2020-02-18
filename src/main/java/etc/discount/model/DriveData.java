package etc.discount.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 走行データ
 */
@Builder
@Value
public class DriveData {
    // FIXME: 割引率を導出するために必要な項目のみ渡す
    private LocalDateTime admissionAt;
    private LocalDateTime exitAt;
    private CarModel model;
}
