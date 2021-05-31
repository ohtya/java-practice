package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
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
 * {@link PrimaryToddler} unit test.
 */
class PrimaryToddlerTest {

    private DiscountRule rule;

    @BeforeEach
    void setUp() {
        rule = new PrimaryToddler();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ArgumentsSource(ApplicableArgumentsProvider.class)
    void isApplicable(final Visitor visitor, final boolean expected) {
        final var actual = rule.isApplicable(visitor);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(DiscountRuleArgumentsProvider.class)
    void discountRate(final LocalDateTime now, final long expected) {
        final var visitor = Visitor.builder()
                .isKaiin(false)
                .age(3)
                .build();
        final var actual = rule.discountRate(now, visitor);
        assertEquals(expected, actual);
    }

    private static class ApplicableArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

            return Stream.of(
                    // 幼児である
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(3)
                                    .build(),
                            true),
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(true)
                                    .age(6)
                                    .build(),
                            true),
                    // 小学生である
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(7)
                                    .build(),
                            true),
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(true)
                                    .age(12)
                                    .build(),
                            true),
                    // 幼児でも小学生でもない
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(2)
                                    .build(),
                            false),
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(true)
                                    .age(13)
                                    .build(),
                            false)
            );
        }
    }

    private static class DiscountRuleArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    // 平日 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 26, 19, 59),
                            1000),
                    // 平日 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 26, 20, 0),
                            1000),
                    // 土 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 24, 19, 59),
                            1000),
                    // 土 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 24, 20, 0),
                            1000),
                    // 日 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 2, 19, 59),
                            1000),
                    // 日 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 2, 20, 0),
                            1000),
// FIXME: 祝日は未実装のため、一旦テストをコメントアウトします
//                // 祝日 20:00 まで
//                Arguments.of(
//                        LocalDateTime.of(2021, 5, 3, 19, 59),
//                        1000),
//                // 祝日 20:00 以降
//                Arguments.of(
//                        LocalDateTime.of(2021, 5, 3, 20, 0),
//                        1000),
                    // 映画の日(毎月1日)
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 1, 0, 0),
                            1000),
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 1, 19, 59),
                            1000),
                    // 映画の日(毎月1日) でもより安い料金に一致する場合はそちらを優先
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 1, 20, 0),
                            1000)
            );
        }
    }
}
