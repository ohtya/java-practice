package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.Drive;
import etc.discount.model.Route;
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
 * {@link Weekday} unit test
 */
class WeekdayTest {

    private DiscountRule rule;

    @BeforeEach
    void setup() {
        rule = new Weekday();
    }

    @ParameterizedTest
    @ArgumentsSource(WeekdayIsApplicableArgumentsProvider.class)
    void isApplicable(final Drive drive, final boolean expected) {
        final var actual = rule.isApplicable(drive);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(WeekdayDiscountRateArgumentsProvider.class)
    void discountRate(final Drive drive, final long expected) {
        final var actual = rule.discountRate(drive);
        assertEquals(expected, actual);
    }
}

class WeekdayIsApplicableArgumentsProvider implements ArgumentsProvider {

    private static final LocalDateTime WEEKDAY_NIGHT_END_AT = LocalDateTime.of(2020, 2, 7, 5, 59);
    private static final LocalDateTime WEEKDAY_MORNING_START_AT = LocalDateTime.of(2020, 2, 7, 6, 0);
    private static final LocalDateTime WEEKDAY_MORNING_END_AT = LocalDateTime.of(2020, 2, 7, 9, 0);
    private static final LocalDateTime WEEKDAY_DAY_START_AT = LocalDateTime.of(2020, 2, 7, 9, 1);
    private static final LocalDateTime WEEKDAY_EVENING_START_AT = LocalDateTime.of(2020, 2, 7, 17, 0);
    private static final LocalDateTime WEEKDAY_EVENING_END_AT = LocalDateTime.of(2020, 2, 7, 20, 0);
    private static final LocalDateTime SATURDAY_MORNING_START_AT = LocalDateTime.of(2020, 2, 8, 6, 0);
    private static final LocalDateTime SATURDAY_MORNING_END_AT = LocalDateTime.of(2020, 2, 8, 9, 0);
    private static final LocalDateTime SUNDAY_EVENING_START_AT = LocalDateTime.of(2020, 2, 9, 17, 0);
    private static final LocalDateTime SUNDAY_EVENING_END_AT = LocalDateTime.of(2020, 2, 9, 20, 0);
    private static final long MIN_COUNT = 0;
    private static final long FIRST_BOUNDARY_COUNT = 4;
    private static final long SECOND_BOUNDARY_COUNT = 5;
    private static final long THIRD_BOUNDARY_COUNT = 9;
    private static final long FINAL_BOUNDARY_COUNT = 10;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_MORNING_START_AT)
                    .exitAt(WEEKDAY_MORNING_END_AT)
                    .route(Route.LOCAL)
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_EVENING_START_AT)
                    .exitAt(WEEKDAY_EVENING_END_AT)
                    .route(Route.LOCAL)
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_NIGHT_END_AT)
                    .exitAt(WEEKDAY_MORNING_START_AT)
                    .route(Route.LOCAL)
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_MORNING_END_AT)
                    .exitAt(WEEKDAY_DAY_START_AT)
                    .route(Route.LOCAL)
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SATURDAY_MORNING_START_AT)
                    .exitAt(SATURDAY_MORNING_END_AT)
                    .route(Route.LOCAL)
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_EVENING_START_AT)
                    .exitAt(SUNDAY_EVENING_END_AT)
                    .route(Route.LOCAL)
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_MORNING_START_AT)
                    .exitAt(WEEKDAY_MORNING_END_AT)
                    .route(Route.URBAN)
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_MORNING_START_AT)
                    .exitAt(WEEKDAY_MORNING_END_AT)
                    .route(Route.LOCAL)
                    .count(MIN_COUNT)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_MORNING_START_AT)
                    .exitAt(WEEKDAY_MORNING_END_AT)
                    .route(Route.LOCAL)
                    .count(FIRST_BOUNDARY_COUNT)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_MORNING_START_AT)
                    .exitAt(WEEKDAY_MORNING_END_AT)
                    .route(Route.LOCAL)
                    .count(THIRD_BOUNDARY_COUNT)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(WEEKDAY_MORNING_START_AT)
                    .exitAt(WEEKDAY_MORNING_END_AT)
                    .route(Route.LOCAL)
                    .count(FINAL_BOUNDARY_COUNT)
                    .build()
                , true)
        );
    }
}

class WeekdayDiscountRateArgumentsProvider implements ArgumentsProvider {

    private static final long MIN_COUNT = 0;
    private static final long FIRST_BOUNDARY_COUNT = 4;
    private static final long SECOND_BOUNDARY_COUNT = 5;
    private static final long THIRD_BOUNDARY_COUNT = 9;
    private static final long FINAL_BOUNDARY_COUNT = 10;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(
                Drive.builder()
                    .count(MIN_COUNT)
                    .build()
                , 0),
            Arguments.of(
                Drive.builder()
                    .count(FIRST_BOUNDARY_COUNT)
                    .build()
                , 0),
            Arguments.of(
                Drive.builder()
                    .count(SECOND_BOUNDARY_COUNT)
                    .build()
                , 30),
            Arguments.of(
                Drive.builder()
                    .count(THIRD_BOUNDARY_COUNT)
                    .build()
                , 30),
            Arguments.of(
                Drive.builder()
                    .count(FINAL_BOUNDARY_COUNT)
                    .build()
                , 50)
        );
    }
}