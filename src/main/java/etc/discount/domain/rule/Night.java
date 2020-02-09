package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.CarModel;
import etc.discount.model.DriveData;

import java.time.LocalTime;

/**
 * 深夜割引
 * https://www.driveplaza.com/traffic/tolls_etc/etc_dis_night/
 * <p>
 * 深夜とは、毎日0時から4時までを指します
 */
public class Night implements DiscountRule {

    private static final LocalTime START_AT = LocalTime.of(0, 0);
    private static final LocalTime END_AT = LocalTime.of(4, 0);

    /**
     * 深夜に走行した場合に適用可能です
     * <p>
     * 全ての {@link CarModel} に適用されます;
     *
     * @return true: 可能, false: 不可
     */
    @Override
    public boolean isApplicable(final DriveData drive) {
        // FIXME: null チェック
        return isNight(drive);
    }

    /**
     * 以下のルールで割引率を適用します
     * - 割引率30%
     *
     * @return 割引率
     */
    @Override
    public long discountRate() {
        return 0;
    }

    private boolean isNight(final DriveData drive) {
        // FIXME: 日跨ぎ
        return !(START_AT.isAfter(drive.getAdmissionAt().toLocalTime()) ||
                END_AT.isBefore(drive.getExitAt().toLocalTime()));
    }
}
