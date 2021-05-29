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
 * {@link CollegeVocationalStudent} unit test.
 * 専門・大学生のテスト
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
    @ArgumentsSource(ApplicableArgumentsProvider.class)
    void isApplicable(final Visitor visitor, final boolean expected) {
        final var actual = rule.isApplicable(visitor);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(DiscountRateArgumentsProvider.class)
    void discountRate(final LocalDateTime now, final long expected){

    }

    private static class ApplicableArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            /* 専門・大学生が適応であるかのテスト */
            return Stream.of(
                    Arguments.of(
                            // 専門学生である
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(20)
                                    .build()
                            , true),
                    Arguments.of(
                            // 大学生である
                            Visitor.builder()
                                    .isKaiin(true)
                                    .age(22)
                                    .build()
                            , true),
                    Arguments.of(
                            // 大学生でも専門学生でもない
                            Visitor.builder()
                                    .isKaiin(true)
                                    .age(18)
                                    .build()
                            , false),
                    Arguments.of(
                            Visitor.builder()
                                    .isKaiin(false)
                                    .age(23)
                                    .build()
                            , false)
            );
        }
    }

    private static class DiscountRateArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(
                            // 平日20時まで
                            LocalDateTime.of(2021,05,28,19,59),
                            1500
                    ),
                    Arguments.of(
                            // 平日20時以降
                            LocalDateTime.of(2021,05,28,20,00),
                            1300
                    ),
                    Arguments.of(
                            // 土曜20時まで
                            LocalDateTime.of(2021,05,29,19,59),
                            1500
                    ),
                    Arguments.of(
                            // 土曜20時以降
                            LocalDateTime.of(2021,05,29,20,00),
                            1300
                    ),
                    Arguments.of(
                            // 日曜20時まで
                            LocalDateTime.of(2021,05,30,19,59),
                            1500
                    ),
                    Arguments.of(
                            // 日曜20時以降
                            LocalDateTime.of(2021,05,30,20,00),
                            1300
                    ),
                    Arguments.of(
                            // 映画の日
                            LocalDateTime.of(2021,05,01,20,00),
                            1100
                    )
            );
        }
    }
}