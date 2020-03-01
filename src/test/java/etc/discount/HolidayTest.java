package etc.discount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link Holiday} unit test
 */
class HolidayTest {

    private Holiday holiday;

    @BeforeEach
    void setup() {
        holiday = new Holiday();
    }

    @ParameterizedTest
    @ArgumentsSource(IsWeekendArgumentsProvider.class)
    void isApplicable(final LocalDateTime date, final boolean expected) {
        final var actual = holiday.isWeekend(date);
        assertEquals(expected, actual);
    }

}

class IsWeekendArgumentsProvider implements ArgumentsProvider {

    private static final LocalDateTime FRIDAY_START_AT = LocalDateTime.of(2020, 2, 7, 0, 0);
    private static final LocalDateTime FRIDAY_END_AT = LocalDateTime.of(2020, 2, 7, 23, 59);
    private static final LocalDateTime SATURDAY_START_AT = LocalDateTime.of(2020, 2, 8, 0, 0);
    private static final LocalDateTime SATURDAY_END_AT = LocalDateTime.of(2020, 2, 8, 23, 59);
    private static final LocalDateTime SUNDAY_START_AT = LocalDateTime.of(2020, 2, 9, 0, 0);
    private static final LocalDateTime SUNDAY_END_AT = LocalDateTime.of(2020, 2, 9, 23, 59);
    private static final LocalDateTime MONDAY_START_AT = LocalDateTime.of(2020, 2, 10, 0, 0);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(
                SATURDAY_START_AT
                , true),
            Arguments.of(
                SATURDAY_END_AT
                , true),
            Arguments.of(
                SUNDAY_START_AT
                , true),
            Arguments.of(
                SUNDAY_END_AT
                , true),
            Arguments.of(
                FRIDAY_START_AT
                , false),
            Arguments.of(
                FRIDAY_END_AT
                , false),
            Arguments.of(
                MONDAY_START_AT
                , false)
        );
    }
}