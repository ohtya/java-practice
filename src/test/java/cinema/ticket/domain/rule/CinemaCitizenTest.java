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
        return Stream.of(
                // シネマシティズン会員である
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.GENERAL)
                                .isKaiin(true)
                                .age(59)
                                .build()
                        , true),
                // シネマシティズン会員ではない
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.GENERAL)
                                .isKaiin(false)
                                .age(59)
                                .build()
                        , false),
                // シネマシティズン会員であるが、シネマシティズンシニア扱いとする
                Arguments.of(
                        Visitor.builder()
                                .visitorType(VisitorType.GENERAL)
                                .isKaiin(true)
                                .age(60)
                                .build()
                        , false)
        );
    }
}
    }
}