package etc.discount.domain.rule;

import etc.discount.Holiday;
import etc.discount.domain.DiscountRule;
import etc.discount.model.CarModel;
import etc.discount.model.Drive;
import etc.discount.model.Route;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 休日割引
 * https://www.driveplaza.com/traffic/tolls_etc/etc_dis_weekend/
 * <p>
 * 休日とは、以下を指します
 * - 土曜日
 * - 日曜日
 * - 祝日
 * - 三が日(毎年1月２日、3日)
 * FIXME: 祝日と三が日を休日の対象にする
 */
public class Weekend implements DiscountRule {

    protected static final List<CarModel> APPLICABLE_CAR_MODEL = Arrays.stream(CarModel.values()).filter(carModel -> carModel.equals(CarModel.KEI) || carModel.equals(CarModel.ORDINARY)).collect(Collectors.toList());
    private Holiday holiday;

    /**
     * 入り口料金所または出口料金所を休日に通過した場合に適用可能です
     * FIXME: 本来は休日跨ぎも適用可だが、面倒なので今回は省略する
     * <p>
     * また {@link CarModel} が「普通車」又は「軽自動車等」に該当する車両が対象です
     * <p>
     * 対象区間は地方部のみです
     *
     * @return true: 可能, false: 不可
     */
    @Override
    public boolean isApplicable(final Drive drive) {
        if (drive == null) {
            throw new IllegalArgumentException("arg1 can not be null.");
        }

        return Objects.equals(drive.getRoute(), Route.LOCAL) &&
            APPLICABLE_CAR_MODEL.contains(drive.getModel()) &&
            (holiday.isWeekend(drive.getAdmissionAt()) || holiday.isWeekend(drive.getExitAt()));
    }

    /**
     * 以下のルールで割引率を適用します
     * - 割引率30%
     *
     * @return 割引率
     */
    @Override
    public long discountRate(final Drive drive) {
        return 30;
    }
}
