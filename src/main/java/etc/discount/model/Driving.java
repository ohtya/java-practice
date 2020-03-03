package etc.discount.model;

import java.time.LocalDateTime;

/**
 * 走行情報クラス
 */
public class Driving {

    public Driving(CarModel type,
                   LocalDateTime enteringDate,
                   LocalDateTime leavingDate,
                   String enteringPoint,
                   String leavingPoint) {
        this.type = type;
        this.enteringDate = enteringDate;
        this.leavingDate = leavingDate;
        this.enteringPoint = enteringPoint;
        this.leavingPoint = leavingPoint;
    }

    // 車種
    private CarModel type;

    private Route route;

    // 入場時刻
    private LocalDateTime enteringDate;

    // 退場時刻
    private LocalDateTime leavingDate;

    // 入場位置
    private String enteringPoint;

    // 退場位置
    private String leavingPoint;

    public LocalDateTime getEnteringDate() {
        return enteringDate;
    }

    public LocalDateTime getLeavingDate() {
        return leavingDate;
    }

    public String getEnteringPoint() {
        return enteringPoint;
    }

    public String getLeavingPoint() {
        return leavingPoint;
    }

    public void setEnteringDate(LocalDateTime enteringDate) {
        this.enteringDate = enteringDate;
    }

    public void setLeavingDate(LocalDateTime leavingDate) {
        this.leavingDate = leavingDate;
    }

    public void setEnteringPoint(String enteringPoint) {
        this.enteringPoint = enteringPoint;
    }

    public void setLeavingPoint(String leavingPoint) {
        this.leavingPoint = leavingPoint;
    }

    public CarModel getType() {
        return type;
    }

    public void setType(CarModel type) {
        this.type = type;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
