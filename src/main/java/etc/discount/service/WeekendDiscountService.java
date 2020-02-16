package etc.discount.service;

import etc.discount.model.CarModel;
import etc.discount.model.DrivingInfo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * 休日割引を実現するサービス.
 */
public class WeekendDiscountService implements DiscountService {
    private static final long DISCOUNT_RATE = 30;

    /**
     * 割引率を計算します
     *
     * @return 割引率
     */
    @Override
    public long calculate(DrivingInfo info) {
        // 車種：軽自動車等、普通車が対象
        if (info.carModel == CarModel.KEI || info.carModel == CarModel.ORDINARY) {
            // 休日利用の判定
            if (isWeekendUse(info)) {
                return DISCOUNT_RATE;
            }
        }
        return 0;
    }

    /**
     * 休日利用かどうか
     * FIXME:休日をまたいでいるかどうかの判定が入っていない
     *
     * @return 休日利用（true:休日利用である, false:休日利用でない）
     */
    private boolean isWeekendUse(DrivingInfo info) {
        // 入場時刻、出場時刻どちらかが土日祝の場合は休日利用である
        if (isWeekend(info.admissionAt) || isWeekend(info.exitAt)) {
            return true;
        }
        // TODO：入場時刻、出場時刻共に休日でなくとも、土日祝をまたいでいる場合の処理を追加する
        return false;
    }

    /**
     * 休日かどうか
     * FIXME:祝日判定が入っていない（土日判定のみ）
     *
     * @return 休日（true:休日である, false:休日でない）
     */
    private boolean isWeekend(LocalDateTime date) {
        DayOfWeek week = date.getDayOfWeek();
        if (week == DayOfWeek.SUNDAY || week == DayOfWeek.SATURDAY) {
            return true;
        }
        return false;
    }
}