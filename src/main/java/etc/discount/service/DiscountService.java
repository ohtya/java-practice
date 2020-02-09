package etc.discount.service;

import etc.discount.model.DrivingInfo;

/**
 * 割引を実現するサービス.
 */
public interface DiscountService {
    /**
     * 割引率を計算します
     *
     * @return 割引率
     */
    long calculate(DrivingInfo info);
}
