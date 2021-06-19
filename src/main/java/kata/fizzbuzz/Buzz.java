package kata.fizzbuzz;

/**
 * 5の倍数の場合は「Buzz」
 */
public class Buzz implements FizzBuzzRule {

    @Override
    public boolean isApplicable(long input) {
        return input % 5 == 0;
    }

    @Override
    public String convert(long input) {
        return FizzBuzzEnum.BUZZ.display();
    }
}
