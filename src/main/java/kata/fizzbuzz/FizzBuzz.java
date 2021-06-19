package kata.fizzbuzz;

/**
 * 3の倍数かつ5の倍数の場合（すなわち15の倍数の場合）は「Fizz Buzz」（Bizz Buzzの場合は「Bizz Buzz」）
 */
public class FizzBuzz implements FizzBuzzRule {

    @Override
    public boolean isApplicable(long input) {
        return input % 15 == 0;
    }

    @Override
    public String convert(long input) {
        return FizzBuzzEnum.FIZZ_BUZZ.display();
    }
}