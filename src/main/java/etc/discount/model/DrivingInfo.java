package etc.discount.model;

import java.time.LocalDateTime;

/**
 * 走行情報
 */
public class DrivingInfo {
    /**
     * 車種
     */
    public CarModel carModel;
    /**
     * 入場時刻
     */
    public LocalDateTime admissionAt;
    /**
     * 出場時刻
     */
    public LocalDateTime exitAt;

    public DrivingInfo(CarModel carModel, LocalDateTime admissionAt, LocalDateTime exitAt){
        this.carModel = carModel;
        this.admissionAt = admissionAt;
        this.exitAt = exitAt;
    }
}