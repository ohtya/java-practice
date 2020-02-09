package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.CarModel;

/**
 * 休日割引
 * https://www.driveplaza.com/traffic/tolls_etc/etc_dis_weekend/
 * <p>
 * 休日とは、以下を指します
 * - 土曜日
 * - 日曜日
 * - 祝日
 * - 三が日(毎年1月２日、3日)
 */
public class Weekend implements DiscountRule {

    /**
     * 入り口料金所または出口料金所を休日に通過した場合に適用可能です
     * <p>
     * また {@link CarModel} が「普通車」又は「軽自動車等」に該当する車両が対象です
     * <p>
     * 対象区間は地方部のみです
     *
     * @return true: 可能, false: 不可
     */
    @Override
    public boolean isApplicable() {
        return false;
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
}
