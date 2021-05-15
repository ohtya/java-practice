package kata.fizzbuzz;

/**
 * FizzBuzzRule
 */
public interface FizzBuzzRule {

    /**
     * 適用可能か？
     *
     * @return true: 可能, false: 不可
     */
    boolean isApplicable(final long input);

    /**
     * 入力値を文字列に変換します
     *
     * @param input 入力値
     * @return 戻り値
     */
    String convert(final long input);
}
