package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.CarModel;
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
 * {@link Weekend} unit test
 */
class WeekendTest {

    private DiscountRule rule;

    @BeforeEach
    void setup() {
        rule = new Weekend();
    }

    @ParameterizedTest
    @ArgumentsSource(WeekEndIsApplicableArgumentsProvider.class)
    void isApplicable(final Drive drive, final boolean expected) {
        final var actual = rule.isApplicable(drive);
        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @ArgumentsSource(WeekendDiscountRateArgumentsProvider.class)
    void discountRate(final Drive drive, final long expected) {
        final var actual = rule.discountRate(drive);
        assertEquals(expected, actual);
    }
}

class WeekEndIsApplicableArgumentsProvider implements ArgumentsProvider {

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
                Drive.builder()
                    .admissionAt(SUNDAY_START_AT)
                    .exitAt(SUNDAY_END_AT)
                    .model(CarModel.KEI)
                    .route(Route.LOCAL)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_START_AT)
                    .exitAt(SUNDAY_END_AT)
                    .model(CarModel.ORDINARY)
                    .route(Route.LOCAL)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_START_AT)
                    .exitAt(SUNDAY_END_AT)
                    .model(CarModel.MIDIUM)
                    .route(Route.LOCAL)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_START_AT)
                    .exitAt(SUNDAY_END_AT)
                    .model(CarModel.LARGE)
                    .route(Route.LOCAL)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_START_AT)
                    .exitAt(SUNDAY_END_AT)
                    .model(CarModel.EXTRA)
                    .route(Route.LOCAL)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SATURDAY_START_AT)
                    .exitAt(SATURDAY_END_AT)
                    .model(CarModel.KEI)
                    .route(Route.LOCAL)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SATURDAY_START_AT)
                    .exitAt(SATURDAY_END_AT)
                    .model(CarModel.ORDINARY)
                    .route(Route.LOCAL)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(FRIDAY_START_AT)
                    .exitAt(FRIDAY_END_AT)
                    .model(CarModel.KEI)
                    .route(Route.LOCAL)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(FRIDAY_START_AT)
                    .exitAt(FRIDAY_END_AT)
                    .model(CarModel.ORDINARY)
                    .route(Route.LOCAL)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_START_AT)
                    .exitAt(SUNDAY_END_AT)
                    .model(CarModel.KEI)
                    .route(Route.URBAN)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_START_AT)
                    .exitAt(SUNDAY_END_AT)
                    .model(CarModel.ORDINARY)
                    .route(Route.URBAN)
                    .build()
                , false),
            Arguments.of(
                Drive.builder()
                    .admissionAt(FRIDAY_END_AT)
                    .exitAt(SATURDAY_START_AT)
                    .model(CarModel.KEI)
                    .route(Route.LOCAL)
                    .build()
                , true),
            Arguments.of(
                Drive.builder()
                    .admissionAt(SUNDAY_END_AT)
                    .exitAt(MONDAY_START_AT)
                    .model(CarModel.KEI)
                    .route(Route.LOCAL)
                    .build()
                , true)
        );
    }
}

class WeekendDiscountRateArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(
                Drive.builder().build()
                , 30)
        );
    }
}