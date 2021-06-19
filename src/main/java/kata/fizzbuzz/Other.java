package kata.fizzbuzz;

/**
 * 入力値をそのまま文字列に変換する<br>
 * 所謂 NullObject
 */
public class Other implements FizzBuzzRule {

    @Override
    public boolean isApplicable(long input) {
        return true;
    }

    @Override
    public String convert(long input) {
        return String.valueOf(input);
    }
}
