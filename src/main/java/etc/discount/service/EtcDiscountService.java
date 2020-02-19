package etc.discount.service;

import etc.discount.model.DrivingInformation;
import etc.discount.model.Route;

import java.time.DayOfWeek;

import static etc.discount.model.CarModel.KEI;
import static etc.discount.model.CarModel.ORDINARY;
import static etc.discount.model.Route.LOCAL;

/**
 * ETC割引を実現するサービス.
 */
public class EtcDiscountService implements DiscountService {

    // 走行情報
    private DrivingInformation info;

    /**
     * コンストラクタ
     */
    public EtcDiscountService(DrivingInformation info) {
        this.info = info;
    }

    /**
     * 料金を計算し、返却する
     *
     * @return 料金
     */
    @Override
    public long calculate(long price) {
        return (long)(price * (1 - this.getRate()));
    }

    /**
     * 割引率を返却する
     *
     * @return 割引率
     */
    private double getRate() {
        int enteringHour = info.getEnteringDate().getHour();
        int leavingHour = info.getLeavingDate().getHour();
        double rate = 0.0;

        // 平日朝夕割引
        if (!this.isHoliday() &&
            (this.isMorning() || this.isEvening()) &&
            (this.isLocal() == LOCAL) ) {
            // TODO ポイントによる減算は未実装、固定で50パーセント割引（還元）とする。
            rate = 0.5;
        } else if ((this.info.getType() == KEI || this.info.getType() == ORDINARY) && // 休日割引
                 (this.isHoliday()) && (this.isLocal() == LOCAL) ){
            rate = 0.3;
        } else if ( this.isMidnight() ) { // 深夜割引
            rate = 0.3;
        } else {
            // その他の割引（未実装）
        }
        return rate;
    }

    /**
     * 休日かどうかを返却する
     *
     * @return
     * True：休日
     * False：平日
     */
    private Boolean isHoliday() {
        DayOfWeek week = info.getEnteringDate().getDayOfWeek();
        return (week == DayOfWeek.SATURDAY || week == DayOfWeek.SUNDAY);
    }

    /**
     * 朝時間であるかどうかを返却する
     *
     * @return 朝時間であるかどうか
     */
    private Boolean isMorning() {
        int enteringHour = info.getEnteringDate().getHour();
        int leavingHour = info.getLeavingDate().getHour();

        // 平日朝夕割引
        return ( (6 <= enteringHour && enteringHour < 9) ||
                 (6 <= leavingHour && leavingHour < 9) );
    }

    /**
     * 夕時間であるかどうかを返却する
     *
     * @return 夕時間であるかどうか
     */
    private Boolean isEvening() {
        int enteringHour = info.getEnteringDate().getHour();
        int leavingHour = info.getLeavingDate().getHour();

        return ( (17 <= enteringHour && enteringHour < 20) ||
                (17 <= leavingHour && leavingHour < 20) );
    }

    /**
     * 深夜時刻であるかを判定する
     *
     * @return
     * True：深夜時間帯
     * False：深夜時間帯外
     */
    private Boolean isMidnight(){
        int enteringHour = info.getEnteringDate().getHour();
        int leavingHour = info.getLeavingDate().getHour();

        return ( ( enteringHour < 4) || ( leavingHour < 4) );
    }

    /**
     * 地方部であるかを判定する。
     *
     * @return 地方部であるかどうか
     */
    private Route isLocal() {
        // TODO 位置情報から都市部か地方部を返却する
        // 現状はわからないので地方部を返却する。
        return LOCAL;
    }

    /**
     * マイレージサービスに登録しているかどうかを判定する
     *
     * @return マイレージサービス登録しているかどうか
     */
    private Boolean isMileage() {
        /* TODO マイレージサービスをDrivingInformationに実装？
            別クラスで実装でDrivingInfoに持たせるとか検討する */
        return true;
    }
}
