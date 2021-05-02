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
}

class CinemaCitizenIsApplicableArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        /* シネマシチズン適応であるかのテスト */
        return Stream.of(
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.GENERAL)
                    .isKaiin(true)
                    .age(59)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.GENERAL)
                    .isKaiin(true)
                    .age(20)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.GENERAL)
                    .isKaiin(false)
                    .age(70)
                    .build()
                , false),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.GENERAL)
                    .isKaiin(false)
                    .age(20)
                    .build()
                , false),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.SENIOR)
                    .isKaiin(true)
                    .age(59)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.COLLEGE)
                    .isKaiin(true)
                    .age(20)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.VOCATIONAL)
                    .isKaiin(true)
                    .age(20)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.HIGH_SCHOOL)
                    .isKaiin(true)
                    .age(18)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.JUNIOR_HIGH_SCHOOL)
                    .isKaiin(true)
                    .age(14)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.PRIMARY_SCHOOL)
                    .isKaiin(true)
                    .age(10)
                    .build()
                , true),
            Arguments.of(
                Visitor.builder()
                    .visitorType(VisitorType.TODDLER)
                    .isKaiin(true)
                    .age(5)
                    .build()
                , true)
            );
    }
}