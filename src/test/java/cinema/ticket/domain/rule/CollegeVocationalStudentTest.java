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
 * {@link CollegeVocationalStudent} unit test.
 */
class CollegeVocationalStudentTest {

    private DiscountRule rule;

    @BeforeEach
    void setUp() {
        rule = new CollegeVocationalStudent();
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
                    // 専門学校生である
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(19)
                                    .build(),
                            true),
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(20)
                                    .build(),
                            true),
                    // 大学生である
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(21)
                                    .build(),
                            true),
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(22)
                                    .build(),
                            true),
                    // 専門学校生でも大学生でもない
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(18)
                                    .build(),
                            false),
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(70)
                                    .build(),
                            false),
                    // 専門学校生だがシネマシティズン会員である
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(true)
                                    .age(20)
                                    .build(),
                            false)
            );
        }
    }

    private static class PriceArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    // 平日 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 26, 19, 59),
                            1500),
                    // 平日 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 26, 20, 0),
                            1300),
                    // 土 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 24, 19, 59),
                            1500),
                    // 土 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 24, 20, 0),
                            1300),
                    // 日 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 2, 19, 59),
                            1500),
                    // 日 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 2, 20, 0),
                            1300),
// FIXME: 祝日は未実装のため、一旦テストをコメントアウトします
//                // 祝日 20:00 まで
//                Arguments.of(
//                        LocalDateTime.of(2021, 5, 3, 19, 59),
//                        1500),
//                // 祝日 20:00 以降
//                Arguments.of(
//                        LocalDateTime.of(2021, 5, 3, 20, 0),
//                        1300),
                    // 映画の日(毎月1日)
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 1, 0, 0),
                            1100),
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 1, 19, 59),
                            1100)
            );
        }
    }
}
