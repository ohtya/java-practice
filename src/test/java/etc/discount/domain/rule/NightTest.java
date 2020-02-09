package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.DriveData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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

    @Nested
    class isApplicable {
        @Test
        void returnFalseThenNotNight() {
            var admissionAt = LocalDateTime.of(2020, 2, 9, 22, 0);
            var exitAt = LocalDateTime.of(2020, 2, 9, 23, 59);
            final var drive = new DriveData(admissionAt, exitAt);
            final var expected = false;
            final var actual = rule.isApplicable(drive);
            assertEquals(expected, actual);
        }

        @Test
        void returnTrueThenNight() {
            var admissionAt = LocalDateTime.of(2020, 2, 10, 0, 0);
            var exitAt = LocalDateTime.of(2020, 2, 10, 4, 0);
            final var drive = new DriveData(admissionAt, exitAt);
            final var expected = true;
            final var actual = rule.isApplicable(drive);
            assertEquals(expected, actual);
        }
    }

    @Test
    void discountRate() {
        final var expected = 0L;
        final var actual = rule.discountRate();
        assertEquals(expected, actual);
    }
}