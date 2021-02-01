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

import static org.junit.jupiter.api.Assertions.*;

/**
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
    @ArgumentsSource(CollegeVocationalStudentIsApplicableArgumentsProvider.class)
    void isApplicable(final Visitor visitor, final boolean expected) {
        final var actual = rule.isApplicable(visitor);
        assertEquals(expected, actual);
    }
}


class CollegeVocationalStudentIsApplicableArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        /* 専門・大学生が適応であるかのテスト */
        return Stream.of(
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
                                .isKaiin(false)
                                .age(20)
                                .build()
                        , true),
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.GENERAL)
                                .isKaiin(true)
                                .age(20)
                                .build()
                        , false),
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.SENIOR)
                                .isKaiin(false)
                                .age(70)
                                .build()
                        , false),
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.HIGH_SCHOOL)
                                .isKaiin(false)
                                .age(17)
                                .build()
                        , false),
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.JUNIOR_HIGH_SCHOOL)
                                .isKaiin(false)
                                .age(13)
                                .build()
                        , false),
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.PRIMARY_SCHOOL)
                                .isKaiin(false)
                                .age(10)
                                .build()
                        , false),
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.TODDLER)
                                .isKaiin(false)
                                .age(5)
                                .build()
                        , false)
        );
    }
}