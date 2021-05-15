package kata.fizzbuzz;

public enum FizzBuzzEnum {

    FIZZ("Fizz"), BUZZ("Buzz"), FIZZ_BUZZ("FizzBuzz");

    private final String display;

    FizzBuzzEnum(final String display) {
        this.display = display;
    }

    public String display() {
        return display;
    }
}
