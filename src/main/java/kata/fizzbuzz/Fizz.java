package kata.fizzbuzz;

/**
 * 3の倍数の場合は「Fizz」（Bizz Buzzの場合は「Bizz」）
 */
public class Fizz implements FizzBuzzRule {

    @Override
    public boolean isApplicable(long input) {
        return input % 3 == 0;
    }

    @Override
    public String convert(long input) {
        return FizzBuzzEnum.FIZZ.display();
    }
}
