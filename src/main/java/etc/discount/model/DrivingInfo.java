package etc.discount.model;

import java.util.Date;

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
    public Date admissionTime;
    /**
     * 出場時刻
     */
    public Date exitTime;

    public DrivingInfo(CarModel carModel, Date admissionTime, Date exitTime){
        this.carModel = carModel;
        this.admissionTime = admissionTime;
        this.exitTime = exitTime;
    }
}