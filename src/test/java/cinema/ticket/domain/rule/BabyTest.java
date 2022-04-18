
package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.domain.MyMovieTheaterScreenTime;
import cinema.ticket.model.Visitor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * {@link Baby} unit test.
 */
@DisplayName("Baby unit test.")
class BabyTest {

    private DiscountRule rule;

    @BeforeEach
    void setUp() {
        rule = new Baby();
    }

    @AfterEach
    void tearDown() {
    }

    // Java 11 の場合は非 static なクラス内に static なクラスやメソッドを定義できないため TestInstance アノテーションを付与します
    @Nested
    @TestInstance(PER_CLASS)
    @DisplayName("お客様に割引を適用可能か")
    class TestOfIsApplicable {

        @ParameterizedTest
        @MethodSource("visitorProvider")
        void isApplicable(final Visitor visitor, final boolean expected) {
            final var actual = rule.isApplicable(visitor);
            assertEquals(expected, actual);
        }

        Stream<Arguments> visitorProvider() {
            return Stream.of(
                    // 乳児である
                    Arguments.of(
                            Visitor.builder()
                                    .age(0)
                                    .build(),
                            true),
                    Arguments.of(
                            Visitor.builder()
                                    .age(2)
                                    .build(),
                            true),
                    // 乳児ではない
                    Arguments.of(
                            Visitor.builder()
                                    .age(3)
                                    .build(),
                            false)
            );
        }
    }

    // Java 11 の場合は非 static なクラス内に static なクラスやメソッドを定義できないため TestInstance アノテーションを付与します
    @Nested
    @TestInstance(PER_CLASS)
    @DisplayName("値段を返す")
    class TestOfPrice {

        @ParameterizedTest
        @MethodSource("nowProvider")
        void price(final LocalDateTime now, final long expected) {
            final var screenTime = MyMovieTheaterScreenTime.builder()
                    .screenTime(now)
                    .build();
            final var actual = rule.price(screenTime);
            assertEquals(expected, actual);
        }

        Stream<Arguments> nowProvider() {
            return Stream.of(
                    // 平日 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 26, 19, 59),
                            0),
                    // 平日 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 26, 20, 0),
                            0),
                    // 土 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 24, 19, 59),
                            0),
                    // 土 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 4, 24, 20, 0),
                            0),
                    // 日 20:00 まで
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 2, 19, 59),
                            0),
                    // 日 20:00 以降
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 2, 20, 0),
                            0),
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
                            0),
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 1, 19, 59),
                            0),
                    // 映画の日(毎月1日) でもより安い料金に一致する場合はそちらを優先
                    Arguments.of(
                            LocalDateTime.of(2021, 5, 1, 20, 0),
                            0)
            );
        }
    }
}
