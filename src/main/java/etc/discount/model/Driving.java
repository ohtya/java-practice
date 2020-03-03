package etc.discount.model;

import etc.discount.state.Night;
import etc.discount.state.TimeMaster;
import etc.discount.state.rule.DayState;

import java.sql.Time;
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
        this.enteringPoint = enteringPoint;
        this.leavingPoint = leavingPoint;
        this.timeMaster = new TimeMaster(enteringDate, leavingDate);
    }

    // 車種
    private CarModel type;

    private Route route;

    // 入場位置
    private String enteringPoint;

    // 退場位置
    private String leavingPoint;

    private TimeMaster timeMaster;

    public String getEnteringPoint() {
        return enteringPoint;
    }

    public String getLeavingPoint() {
        return leavingPoint;
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

    public TimeMaster getTimeMaster() {
        return timeMaster;
    }

    public void setTimeMaster(TimeMaster timeMaster) {
        this.timeMaster = timeMaster;
    }
}
