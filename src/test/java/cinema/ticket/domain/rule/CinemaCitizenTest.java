package cinema.ticket.domain.rule;

import cinema.ticket.domain.DiscountRule;
import cinema.ticket.model.Visitor;
import cinema.ticket.model.VisitorType;
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
 * {@link CinemaCitizen} unit test.
 */
class CinemaCitizenTest {

    private DiscountRule rule;

    @BeforeEach
    void setUp() {
        rule = new CinemaCitizen();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ArgumentsSource(CinemaCitizenIsApplicableArgumentsProvider.class)
    void isApplicable(final Visitor visitor, final boolean expected) {
        final var actual = rule.isApplicable(visitor);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(CinemaCitizenDiscountRateArgumentsProvider.class)
    void discountRate(final LocalDateTime now, final long expected) {
        final var visitor = Visitor.builder()
                .visitorType(VisitorType.GENERAL)
                .isKaiin(true)
                .age(59)
                .build();
        final var actual = rule.discountRate(now, visitor);
        assertEquals(expected, actual);
    }
}

class CinemaCitizenIsApplicableArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                // シネマシティズン会員である
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.GENERAL)
                                .isKaiin(true)
                                .age(59)
                                .build(),
                        true),
                // シネマシティズン会員ではない
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.GENERAL)
                                .isKaiin(false)
                                .age(59)
                                .build(),
                        false),
                // シネマシティズン会員であるが、シネマシティズンシニア扱いとする
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.GENERAL)
                                .isKaiin(true)
                                .age(60)
                                .build(),
                        false)
        );
    }
}

class CinemaCitizenDiscountRateArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                // 平日 20:00 までは 1,000 円
                Arguments.of(
                        LocalDateTime.of(2021, 4, 26, 19, 59),
                        1000),
                // 平日 20:00 以降は 1,000 円
                Arguments.of(
                        LocalDateTime.of(2021, 4, 26, 20, 0),
                        1000),
                // 土 20:00 までは 1,300 円
                Arguments.of(
                        LocalDateTime.of(2021, 4, 24, 19, 59),
                        1300),
                // 土 20:00 以降は 1,000 円
                Arguments.of(
                        LocalDateTime.of(2021, 4, 24, 20, 0),
                        1000),
                // 日 20:00 までは 1,300 円
                Arguments.of(
                        LocalDateTime.of(2021, 5, 2, 19, 59),
                        1300),
                // 日 20:00 以降は 1,000 円
                Arguments.of(
                        LocalDateTime.of(2021, 5, 2, 20, 0),
                        1000),
// FIXME: 祝日は未実装のため、一旦テストをコメントアウトします
//                // 祝日 20:00 までは 1,300 円
//                Arguments.of(
//                        LocalDateTime.of(2021, 5, 3, 19, 59),
//                        1300),
//                // 祝日 20:00 以降は 1,000 円
//                Arguments.of(
//                        LocalDateTime.of(2021, 5, 3, 20, 0),
//                        1000),
                // 映画の日(毎月1日) 時間帯によらず 1,100 円
                Arguments.of(
                        LocalDateTime.of(2021, 5, 1, 0, 0),
                        1100),
                Arguments.of(
                        LocalDateTime.of(2021, 5, 1, 19, 59),
                        1100),
                // 映画の日(毎月1日) でもより安い料金に一致する場合はそちらを優先
                Arguments.of(
                        LocalDateTime.of(2021, 5, 1, 20, 00),
                        1000)
        );
    }
}