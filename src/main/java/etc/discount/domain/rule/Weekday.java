package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.DriveData;

/**
 * 平日朝夕割引
 * https://www.driveplaza.com/traffic/tolls_etc/etc_dis_weekday/
 * <p>
 * 平日とは、月曜日から金曜日までを指します
 * 但し、祝日は除外します
 */
public class Weekday implements DiscountRule {

    /**
     * 入り口料金所または出口料金所を平日の以下の時間帯に通過した場合に適用可能です
     * - 朝(6時から9時まで)
     * - 夕(17時から20時まで)
     * <p>
     * また、1か月の利用回数が5回以上の場合に適用可能です
     * 1か月は、1日から末日までとします
     * <p>
     * 対象区間は地方部のみです
     *
     * @return true: 可能, false: 不可
     */
    @Override
    public boolean isApplicable(final DriveData drive) {
        return false;
    }

    /**
     * 以下のルールで割引率を適用します
     * - 利用回数が5回から9回までの場合、還元率30%
     * - 利用回数が10回以上の場合、還元率50%
     *
     * @return 割引率(還元率)
     */
    @Override
    public long discountRate() {
        return 0;
    }
}
