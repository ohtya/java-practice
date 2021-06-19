package kata.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * {@link FizzBuzzServiceTest} unit test.
 */
class FizzBuzzServiceTest {

    private FizzBuzzService service;

    @BeforeEach
    void setup() {
        service = new FizzBuzzService();
    }

    /**
     * 入力値が3の倍数の場合 Fizz を返す
     */
    @Test
    void test_fizz() {
        final var input = 3;
        final var expected = "Fizz";
        final var actual = service.execute(input);
        assertEquals(expected, actual);
    }

    /**
     * 入力値が5の倍数の場合 Buzz を返す
     */
    @Test
    void test_buzz() {
        final var input = 5;
        final var expected = "Buzz";
        final var actual = service.execute(input);
        assertEquals(expected, actual);
    }

    /**
     * 入力値が15の倍数の場合 FizzBuzz を返す
     */
    @Test
    void test_fizzBuzz() {
        final var input = 15;
        final var expected = "FizzBuzz";
        final var actual = service.execute(input);
        assertEquals(expected, actual);
    }

    /**
     * 入力値が上記以外の場合 入力値と同じ値 を返す
     */
    @Test
    void test_other() {
        final var input = 1;
        final var expected = "1";
        final var actual = service.execute(input);
        assertEquals(expected, actual);
    }
}

