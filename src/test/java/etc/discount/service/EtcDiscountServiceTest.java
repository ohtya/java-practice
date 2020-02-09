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
        final long expected = 0;
        final long actual = service.add(0, 0);
        assertEquals(expected, actual);
    }
}