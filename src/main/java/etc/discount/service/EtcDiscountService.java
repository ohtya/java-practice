package etc.discount.service;

import etc.discount.Week;
import etc.discount.model.Driving;
import etc.discount.state.Daytime;
import etc.discount.state.Holiday;
import etc.discount.state.Night;
import etc.discount.state.Weekday;

import java.time.DayOfWeek;

import static etc.discount.model.CarModel.KEI;
import static etc.discount.model.CarModel.ORDINARY;
import static etc.discount.model.Route.LOCAL;

/**
 * ETC割引を実現するサービス.
 */
public class EtcDiscountService implements DiscountService {

    private Week week = new Week();

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
        return  ( info.getTimeMaster().getCalState() instanceof Weekday &&
                ( info.getTimeMaster().getDayState() instanceof Daytime) &&
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
        return ((week.isApplicable( info.getType() )) &&
                (info.getTimeMaster().getDayState() instanceof Holiday) &&
                (info.getRoute()== LOCAL) );
    }

    /**
     * 深夜時刻であるかを判定する
     *
     * @return
     * True：深夜時間帯
     * False：深夜時間帯外
     */
    private Boolean isMidnight(Driving info){
        return ( info.getTimeMaster().getDayState() instanceof Night);
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
