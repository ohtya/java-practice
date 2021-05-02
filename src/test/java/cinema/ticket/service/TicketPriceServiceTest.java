package cinema.ticket.service;

import cinema.ticket.model.TicketPriceRequest;
import cinema.ticket.model.TicketPriceResponse;
import cinema.ticket.model.Visitor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link TicketPriceService} unit test.
 */
class TicketPriceServiceTest {

    // FIXME: 上映開始と上映終了時間の定義が仕様上存在しない（まだ定義していない）
    // FIXME: このサービスでは事前に除外された前提で、網羅的に処理できるようにしておきます
    private final LocalDateTime WEEKDAYS_START = LocalDateTime.of(2021, 4, 26, 0, 0);
    private final LocalDateTime WEEKDAYS_END = LocalDateTime.of(2021, 4, 26, 19, 59);
    private final LocalDateTime WEEKDAYS_LATE_SHOW_START = LocalDateTime.of(2021, 4, 26, 20, 0);
    private final LocalDateTime WEEKDAYS_LATE_SHOW_END = LocalDateTime.of(2021, 4, 26, 23, 59);
    private final LocalDateTime SATURDAY_START = LocalDateTime.of(2021, 4, 24, 0, 0);
    private final LocalDateTime SATURDAY_END = LocalDateTime.of(2021, 4, 24, 19, 59);
    private final LocalDateTime SATURDAY_LATE_SHOW_START = LocalDateTime.of(2021, 4, 24, 20, 0);
    private final LocalDateTime SATURDAY_LATE_SHOW_END = LocalDateTime.of(2021, 4, 24, 23, 59);
    private final LocalDateTime SUNDAY_START = LocalDateTime.of(2021, 5, 2, 0, 0);
    private final LocalDateTime SUNDAY_END = LocalDateTime.of(2021, 5, 2, 19, 59);
    private final LocalDateTime SUNDAY_LATE_SHOW_START = LocalDateTime.of(2021, 5, 2, 20, 0);
    private final LocalDateTime SUNDAY_LATE_SHOW_END = LocalDateTime.of(2021, 5, 2, 23, 59);
    private final LocalDateTime PUBLIC_HOLIDAY_START = LocalDateTime.of(2021, 5, 3, 0, 0);
    private final LocalDateTime PUBLIC_HOLIDAY_END = LocalDateTime.of(2021, 5, 3, 19, 59);
    private final LocalDateTime PUBLIC_HOLIDAY_LATE_SHOW_START = LocalDateTime.of(2021, 5, 3, 20, 0);
    private final LocalDateTime PUBLIC_HOLIDAY_LATE_SHOW_END = LocalDateTime.of(2021, 5, 3, 23, 59);
    private final LocalDateTime MOVIE_DAY_START = LocalDateTime.of(2021, 5, 1, 0, 0);
    private final LocalDateTime MOVIE_DAY_END = LocalDateTime.of(2021, 5, 1, 19, 59);
    private final LocalDateTime MOVIE_DAY_LATE_SHOW_START = LocalDateTime.of(2021, 5, 1, 20, 0);
    private final LocalDateTime MOVIE_DAY_LATE_SHOW_END = LocalDateTime.of(2021, 5, 1, 23, 59);

    private final PriceService service = new TicketPriceService();

    /**
     * {@link Visitor} 一覧が Empty の場合でもクラッシュしないことを確認します
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListIsEmpty() {
        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_START)
                .visitorList(List.of())
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(0L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }


    /**
     * 乳児
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInBaby() {
        final var toddler = Visitor.builder()
                .age(2)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_START)
                .visitorList(List.of(toddler))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(0L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * 幼児
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInToddler() {
        final var toddler = Visitor.builder()
                .age(6)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_START)
                .visitorList(List.of(toddler))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1000L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * 小学生
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInPrimarySchool() {
        final var primarySchool = Visitor.builder()
                .age(10)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_LATE_SHOW_START)
                .visitorList(List.of(primarySchool))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1000L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * 中学生
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInJuniorHighSchool() {
        final var juniorHighSchool = Visitor.builder()
                .age(14)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_LATE_SHOW_START)
                .visitorList(List.of(juniorHighSchool))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1000L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * 高校生
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInHighSchool() {
        final var highSchool = Visitor.builder()
                .age(18)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_END)
                .visitorList(List.of(highSchool))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1000L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * 以下の条件に合致する場合の総料金が仕様通りであることを確認します
     * - 専門学校生
     * - 映画の日
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInVocational() {
        final var vocational = Visitor.builder()
                .age(19)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(MOVIE_DAY_END)
                .visitorList(List.of(vocational))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1100L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * 大学生
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInCollege() {
        final var college = Visitor.builder()
                .age(22)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(SUNDAY_LATE_SHOW_END)
                .visitorList(List.of(college))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1300L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * シニア
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInSenior() {
        final var senior = Visitor.builder()
                .age(70)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_START)
                .visitorList(List.of(senior))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1100L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * 一般
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInGeneral() {
        final var general = Visitor.builder()
                .age(30)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(SATURDAY_END)
                .visitorList(List.of(general))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1800L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * シネマシティズン(シニア)
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInCinemaCitizenSenior() {
        final var cinemaCitizenSenior = Visitor.builder()
                .age(60)
                .isKaiin(true)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(SUNDAY_START)
                .visitorList(List.of(cinemaCitizenSenior))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1000L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

    /**
     * シネマシティズン
     */
    @Test
    void test_price_nonThrowExceptionThenVisitorListInCinemaCitizen() {
        final var cinemaCitizen = Visitor.builder()
                .age(59)
                .isKaiin(true)
                .build();

        final var request = TicketPriceRequest.builder()
                .targetDateTime(WEEKDAYS_START)
                .visitorList(List.of(cinemaCitizen))
                .build();
        final var expected = TicketPriceResponse.builder()
                .totalPrice(1000L)
                .build();

        final var actual = service.price(request);
        assertEquals(expected, actual);
    }

}