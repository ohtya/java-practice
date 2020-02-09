package etc.discount.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link EtcDiscountService} unit test.
 */
class EtcDiscountServiceTest {

    private DiscountService service;

    @BeforeEach
    void setup() {
        service = new EtcDiscountService();
    }

    @Test
    void calculate() {
        final long expected = 0;
        final long actual = service.calculate();
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        final long x = 1;
        final long y = 2;
        final long expected = x + y;
        final long actual = service.add(x, y);
        assertEquals(expected, actual);
    }
}