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
 * {@link General} unit test.
 */
class GeneralTest {

    private DiscountRule rule;

    @BeforeEach
    void setUp() {
        rule = new General();
    }

    @AfterEach
    void tearDown() {

    }

    @ParameterizedTest
    @ArgumentsSource(IsApplicableArgumentsProvider.class)
    void isApplicable(final Visitor visitor, final boolean expected) {
        final var actual = rule.isApplicable(visitor);
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @ArgumentsSource(DiscountRuleArgumentsProvider.class)
    void discountRule(final LocalDateTime now, final long expected) {
        final var visitor = Visitor.builder()
                .age(23)
                .isKaiin(false)
                .syougaisya(false)
                .build();
        var actual = rule.discountRate(now, visitor);
        assertEquals(expected, actual);
    }

    private static class IsApplicableArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                // 一般に当てはまるケース
                Arguments.of(
                    Visitor.builder()
                        .age(23)
                        .isKaiin(false)
                        .syougaisya(false)
                        .build(),
                    true
                ),
                // 一般に当てはまるケース
                Arguments.of(
                    Visitor.builder()
                        .age(69)
                        .isKaiin(false)
                        .syougaisya(false)
                        .build(),
                    true
                ),
                /* 一般に当てはまらないケース */
                // 大学生
                Arguments.of(
                    Visitor.builder()
                        .age(22)
                        .isKaiin(false)
                        .syougaisya(false)
                        .build(),
                    false
                ),
                // シニア
                Arguments.of(
                    Visitor.builder()
                        .age(70)
                        .isKaiin(false)
                        .syougaisya(false)
                        .build(),
                    false
                ),
                // 会員
                Arguments.of(
                    Visitor.builder()
                        .age(23)
                        .isKaiin(true)
                        .syougaisya(false)
                        .build(),
                    false
                ),
                // 障碍者
                Arguments.of(
                    Visitor.builder()
                        .age(23)
                        .isKaiin(false)
                        .syougaisya(true)
                        .build(),
                    false
                )
            );
        }
    }

    private static class DiscountRuleArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                // 平日20時まで
                Arguments.of(
                    LocalDateTime.of(2021,05,31,19,59),
                    1800L
                ),
                // 平日20時以降
                Arguments.of(
                    LocalDateTime.of(2021,05,31,20,00),
                    1300L
                ),
                // 土曜20時まで
                Arguments.of(
                    LocalDateTime.of(2021,05,29,19,59),
                    1800L
                ),
                // 土曜20以降
                Arguments.of(
                    LocalDateTime.of(2021,05,29,20,00),
                    1300L
                ),
                // 日曜20時まで
                Arguments.of(
                    LocalDateTime.of(2021,05,30,19,59),
                    1800L
                ),
                // 日曜20時以降
                Arguments.of(
                    LocalDateTime.of(2021,05,30,20,00),
                    1300L
                ),
                // 映画の日
                Arguments.of(
                    LocalDateTime.of(2021,05,01,12,00),
                    1100L
                )
            );
        }
    }
}
