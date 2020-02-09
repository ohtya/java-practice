package etc.discount.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

// FIXME: コンストラクタは削除する

/**
 * 走行データ
 */
@AllArgsConstructor
@Builder
@Value
public class DriveData {
    // FIXME: 割引率を導出するために必要な項目のみ渡す
    private LocalDateTime admissionAt;
    private LocalDateTime exitAt;
}