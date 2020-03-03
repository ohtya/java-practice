package etc.discount.service;

import etc.discount.model.Driving;

import java.time.DayOfWeek;

import static etc.discount.model.CarModel.KEI;
import static etc.discount.model.CarModel.ORDINARY;
import static etc.discount.model.Route.LOCAL;

/**
 * ETC割引を実現するサービス.
 */
public class EtcDiscountService implements DiscountService {

    // 走行情報
    // private Driving info;

    @Override
    public long add(long x, long y) {
        return x + y;
    }


    /**
     * 料金を計算し、返却する
     *
     * @return 料金
     */
    @Override
    public long calculate(long price, Driving info) {
        return (long)(price * (1 - this.getRate(info)));
    }

    /**
     * 割引率を返却する
     *
     * @return 割引率
     */
    private double getRate(Driving info) {
        int enteringHour = info.getEnteringDate().getHour();
        int leavingHour = info.getLeavingDate().getHour();
        double rate = 0.0;

        // 平日朝夕割引
        if (this.isWeekday(info) ) {
            // TODO ポイントによる減算は未実装、固定で50パーセント割引（還元）とする。
            rate = 0.5;
        } else if ( this.isHoliday(info)) { // 休日割引
            rate = 0.3;
        } else if ( this.isMidnight(info) ) { // 深夜割引
            rate = 0.3;
        } else {
            // その他の割引（未実装）
        }
        return rate;
    }

    /**
     * 平日の割引対象であるかを返却する。
     *
     * @param info
     * @return
     */
    private Boolean isWeekday(Driving info) {
        return  (!this.isHoliday(info) &&
                (this.isMorning(info) || this.isEvening(info)) &&
                (info.getRoute() == LOCAL) );
    }

    /**
     * 休日かどうかを返却する
     *
     * @return
     * True：休日
     * False：平日
     */
    private Boolean isHoliday(Driving info) {
        DayOfWeek week = info.getEnteringDate().getDayOfWeek();
        return ((info.getType() == KEI || info.getType() == ORDINARY) &&
                (week == DayOfWeek.SATURDAY || week == DayOfWeek.SUNDAY) && (info.getRoute()== LOCAL) );
    }

    /**
     * 朝時間であるかどうかを返却する
     *
     * @return 朝時間であるかどうか
     */
    private Boolean isMorning(Driving info) {
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
    private Boolean isEvening(Driving info) {
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
    private Boolean isMidnight(Driving info){
        int enteringHour = info.getEnteringDate().getHour();
        int leavingHour = info.getLeavingDate().getHour();

        return ( ( enteringHour < 4) || ( leavingHour < 4) );
    }

    /**
     * マイレージサービスに登録しているかどうかを判定する
     *
     * @return マイレージサービス登録しているかどうか
     */
    private Boolean isMileage(Driving info) {
        /* TODO マイレージサービスをDrivingInformationに実装？
            別クラスで実装でDrivingInfoに持たせるとか検討する */
        return true;
    }
}
