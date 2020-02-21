package etc.discount.service;

import etc.discount.model.CarModel;
import etc.discount.model.Drive;
import etc.discount.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @ArgumentsSource(EtcDiscountServiceCalculateArgumentsProvider.class)
    void discountRate(final Drive drive, final long expected) {
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

class EtcDiscountServiceCalculateArgumentsProvider implements ArgumentsProvider {

    private static final LocalDateTime SUNDAY_START_AT = LocalDateTime.of(2020, 2, 9, 0, 0);
    private static final LocalDateTime SUNDAY_END_AT = LocalDateTime.of(2020, 2, 9, 23, 59);

    // FIXME: 各割引1パターンずつ実施する
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
                , 30)
        );
    }
}