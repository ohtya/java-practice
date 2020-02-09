package etc.discount.service;

/**
 * 割引を実現するサービス.
 */
public interface DiscountService {
    /**
     * 割引率を計算します
     * FIXME: パラメータ
     *
     * @return 割引率
     */
    long calculate();

    /**
     * JUnit Sample
     *
     * @param x 加算する値
     * @param y 加算する値
     * @return x + y
     */
    long add(long x, long y);
}
