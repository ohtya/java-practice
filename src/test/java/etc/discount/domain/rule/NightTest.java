package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Night} unit test
 */
class NightTest {

    private DiscountRule rule;

    @BeforeEach
    void setup() {
        rule = new Night();
    }

    @Test
    void isApplicable() {
        final var expected = false;
        final var actual = rule.isApplicable();
        assertEquals(expected, actual);
    }

    @Test
    void discountRate() {
        final var expected = 0L;
        final var actual = rule.discountRate();
        assertEquals(expected, actual);
    }
}