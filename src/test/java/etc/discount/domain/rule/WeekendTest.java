package etc.discount.domain.rule;

import etc.discount.domain.DiscountRule;
import etc.discount.model.CarModel;
import etc.discount.model.DriveData;
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
 * {@link Weekend} unit test
 */
class WeekendTest {

    private DiscountRule rule;

    @BeforeEach
    void setup() {
        rule = new Weekend();
    }

    @ParameterizedTest
    @ArgumentsSource(IsApplicableArgumentsProvider.class)
    void isApplicable(final DriveData drive, final boolean expected) {
        final var actual = rule.isApplicable(drive);
        assertEquals(expected, actual);
    }

    @Test
    void returnDiscountRate() {
        final var expected = 30L;
        final var actual = rule.discountRate();
        assertEquals(expected, actual);
    }
}

class IsApplicableArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 9, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 9, 23, 59))
                    .model(CarModel.KEI)
                    .build()
                , true),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 9, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 9, 23, 59))
                    .model(CarModel.ORDINARY)
                    .build()
                , true),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 9, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 9, 23, 59))
                    .model(CarModel.MIDIUM)
                    .build()
                , false),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 9, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 9, 23, 59))
                    .model(CarModel.LARGE)
                    .build()
                , false),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 9, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 9, 23, 59))
                    .model(CarModel.EXTRA)
                    .build()
                , false),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 8, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 8, 23, 59))
                    .model(CarModel.KEI)
                    .build()
                , true),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 8, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 8, 23, 59))
                    .model(CarModel.ORDINARY)
                    .build()
                , true),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 7, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 7, 23, 59))
                    .model(CarModel.KEI)
                    .build()
                , false),
            Arguments.of(
                DriveData.builder()
                    .admissionAt(LocalDateTime.of(2020, 2, 7, 0, 0))
                    .exitAt(LocalDateTime.of(2020, 2, 7, 23, 59))
                    .model(CarModel.ORDINARY)
                    .build()
                , false)
        );
    }
}