package etc.discount.service;

import etc.discount.model.Drive;

/**
 * 割引を実現するサービス.
 */
public interface DiscountService {

    /**
     * 割引率を計算します
     *
     * @param drive {@link Drive}
     * @return 割引率
     */
    long calculate(final Drive drive);

    /**
     * 与えられた値を加算します(JUnit Sample)
     *
     * @param x 加算する値
     * @param y 加算する値
     * @return x + y
     */
    long add(long x, long y);
}
