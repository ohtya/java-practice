package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.MyMovieTheaterScreenTime;
import cinema.ticket.model.Visitor;
import org.junit.jupiter.api.AfterEach;
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
 * {@link Senior} unit test.
 */
public class SeniorTest {

    private DiscountRule rule;

    @BeforeEach
    void setUp() {
        rule = new Senior();
    }

    @AfterEach
    void tearDown() {

    }

    @ParameterizedTest
    @ArgumentsSource(IsApplicableArgumentsProvider.class)
    void isApplicable(final Visitor visitor, final boolean expected) {
        final var actual = rule.isApplicable(visitor);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(PriceArgumentsProvider.class)
    void price(final LocalDateTime now, final long expected) {
        final var screenTime = MyMovieTheaterScreenTime.builder()
                .screenTime(now)
                .build();
        final var actual = rule.price(screenTime);
        assertEquals(expected, actual);
    }

    private static class IsApplicableArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    // シニアである
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(70)
                                    .build(),
                            true),
                    /* シニアでない */
                    // シネマシティズンシニアとなるケース
                    Arguments.of(
                            Visitor.builder()
                                    .age(70)
                                    .isKaiin(true)
                                    .build(),
                            false
                    ),
                    // 単純にシニアの年齢対象ではない
                    Arguments.of(
                            Visitor.builder()
                                    .age(69)
                                    .isKaiin(false)
                                    .build(),
                            false
                    ),
                    Arguments.of(
                            Visitor.builder()
                                    .age(19)
                                    .isKaiin(false)
                                    .build(),
                            false
                    )
            );
        }
    }

    private static class PriceArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    // 平日20時まで
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 28, 19, 59),
                            1100
                    ),
                    // 平日20時以降
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 28, 20, 00),
                            1100
                    ),
                    // 土曜20時まで
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 29, 19, 59),
                            1100
                    ),
                    // 土曜20時以降
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 29, 20, 00),
                            1100
                    ),
                    // 日曜20時まで
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 30, 19, 59),
                            1100
                    ),
                    // 日曜20時以降
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 30, 20, 00),
                            1100
                    )
            );
        }
    }
}
