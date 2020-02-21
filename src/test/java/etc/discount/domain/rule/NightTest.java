package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.Drive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Night} unit test
 * FIXME: parameterized test 化
 */
class NightTest {

    private DiscountRule rule;

    @BeforeEach
    void setup() {
        rule = new Night();
    }

    @Nested
    class IsApplicable {

        @Test
        void returnFalseTheBeforeNight() {
            var admissionAt = LocalDateTime.of(2020, 2, 9, 22, 0);
            var exitAt = LocalDateTime.of(2020, 2, 9, 23, 59);
            final var drive = Drive.builder()
                .admissionAt(admissionAt)
                .exitAt(exitAt)
                .build();
            final var expected = false;
            final var actual = rule.isApplicable(drive);
            assertEquals(expected, actual);
        }

        @Test
        void returnTrueThenNight() {
            var admissionAt = LocalDateTime.of(2020, 2, 10, 0, 0);
            var exitAt = LocalDateTime.of(2020, 2, 10, 4, 0);
            final var drive = Drive.builder()
                .admissionAt(admissionAt)
                .exitAt(exitAt)
                .build();
            final var expected = true;
            final var actual = rule.isApplicable(drive);
            assertEquals(expected, actual);
        }

        @Test
        void returnFalseTheAfterNight() {
            var admissionAt = LocalDateTime.of(2020, 2, 10, 4, 1);
            var exitAt = LocalDateTime.of(2020, 2, 10, 23, 59);
            final var drive = Drive.builder()
                .admissionAt(admissionAt)
                .exitAt(exitAt)
                .build();
            final var expected = false;
            final var actual = rule.isApplicable(drive);
            assertEquals(expected, actual);
        }

        // FIXME: 本来は日マタギもありえる
        @Test
        void returnFalseTheIntoNight() {
            var admissionAt = LocalDateTime.of(2020, 2, 9, 4, 1);
            var exitAt = LocalDateTime.of(2020, 2, 10, 23, 59);
            final var drive = Drive.builder()
                .admissionAt(admissionAt)
                .exitAt(exitAt)
                .build();
            final var expected = false;
            final var actual = rule.isApplicable(drive);
            assertEquals(expected, actual);
        }
    }

    @Nested
    class DiscountRate {

        @Test
        void returnDiscountRate() {
            final var expected = 30L;
            final var actual = rule.discountRate(Drive.builder().build());
            assertEquals(expected, actual);
        }
    }
}