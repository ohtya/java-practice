package etc.discount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Weekend} unit test
 */
class WeekendTest {

    private Weekend weekend;

    @BeforeEach
    void setup() {
        weekend = new Weekend();
    }

    @Test
    void isWeekend() {
        final var today = LocalDate.of(2020, 2, 7);
        // FIXME
        final var expected = true;
        final var actual = weekend.isWeekend(today);
        assertEquals(expected, actual);

//        today = LocalDate.of(2020, 2, 8);
//        expected = true;
//        actual = weekend.isWeekend(today);
//        assertEquals(expected, actual);
//
//        today = LocalDate.of(2020, 2, 9);
//        expected = true;
//        actual = weekend.isWeekend(today);
//        assertEquals(expected, actual);
//
//        today = LocalDate.of(2020, 2, 10);
//        expected = true;
//        actual = weekend.isWeekend(today);
//        assertEquals(expected, actual);
    }
}