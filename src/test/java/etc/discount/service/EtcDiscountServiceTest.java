package etc.discount.service;

import etc.discount.model.DriveData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
        var admissionAt = LocalDateTime.of(2020, 2, 10, 0, 0);
        var exitAt = LocalDateTime.of(2020, 2, 10, 0, 0);
        final var drive = new DriveData(admissionAt, exitAt);
        final var expected = 0;
        final var actual = service.calculate(drive);
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        final var x = 1;
        final var y = 2;
        final var expected = x + y;
        final var actual = service.add(x, y);
        assertEquals(expected, actual);
    }
}