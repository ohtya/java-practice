package kata.fizzbuzz;

import java.util.List;

/**
 * みんな大好きFizzBuzz<br>
 * https://ja.wikipedia.org/wiki/Fizz_Buzz より<br>
 * <br>
 * 最初のプレイヤーは「1」と数字を発言する。<br>
 * 次のプレイヤーは直前のプレイヤーの次の数字に1を足した数字を発言していく。<br>
 * ただし<br>
 * <br>
 * 3の倍数の場合は「Fizz」（Bizz Buzzの場合は「Bizz」）<br>
 * 5の倍数の場合は「Buzz」<br>
 * 3の倍数かつ5の倍数の場合（すなわち15の倍数の場合）は「Fizz Buzz」（Bizz Buzzの場合は「Bizz Buzz」）<br>
 * <br>
 * を数の代わりに発言しなければならない。
 */
public class FizzBuzzService {

    private final List<FizzBuzzRule> rules = List.of(new FizzBuzz(), new Fizz(), new Buzz());

    /**
     * FizzBuzz ゲーム
     *
     * @param input 入力値
     * @return 戻り値
     */
    public String execute(final long input) {
        return rules.stream()
                .filter(fizzBuzzRule -> fizzBuzzRule.isApplicable(input))
                .findFirst()
                .map(fizzBuzzRule -> fizzBuzzRule.convert(input))
                .orElse(new Other().convert(input));
    }
}
