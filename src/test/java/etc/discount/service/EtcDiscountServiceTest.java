package etc.discount.service;

import etc.discount.model.Driving;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static etc.discount.model.CarModel.KEI;
import static etc.discount.model.CarModel.ORDINARY;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link EtcDiscountService} unit test.
 */
class EtcDiscountServiceTest {

    private DiscountService service;

    @BeforeEach
    void setup() {
//        LocalDateTime dt = new LocalDateTime();
//        DrivingInformation(dt, dt,"", "")

    }

    @Test
    void add() {
        final var x = 1;
        final var y = 2;
        final var expected = x + y;
        final var actual = service.add(x, y);
        assertEquals(expected, actual);
    }

    /**
     * 平日朝夕割引
     */
    @Test
    void calculate_1() {
        var expected = 500;
        long price = 1000;

        // 平日割引 朝 入場時刻
        LocalDateTime enterDt = LocalDateTime.of(2020,2,19,6,00);
        LocalDateTime leavingDt = LocalDateTime.of(2020,2,19, 9,00);
        Driving info = new Driving(ORDINARY, enterDt,leavingDt,"","");
        service = new EtcDiscountService();
        var actual = service.calculate(price, info);
        System.out.println(actual);
        assertEquals(expected, actual);

        // 平日割引 朝　退場時刻
        enterDt = LocalDateTime.of(2020,2,19,5,59);
        leavingDt = LocalDateTime.of(2020,2,19,8,59);
        info = new Driving(ORDINARY, enterDt,leavingDt,"","");
        service = new EtcDiscountService();
        actual = service.calculate(price, info);
        assertEquals(expected, actual);

        // 平日割引 夕　入場時刻
        enterDt = LocalDateTime.of(2020,2,19,17,00);
        leavingDt = LocalDateTime.of(2020,2,19,20,00);
        info = new Driving(ORDINARY, enterDt,leavingDt,"","");
        service = new EtcDiscountService();
        actual = service.calculate(price, info);
        assertEquals(expected, actual);

        // 平日割引 夕　退場時刻
        enterDt = LocalDateTime.of(2020,2,19,16,59);
        leavingDt = LocalDateTime.of(2020,2,19,19,59);
        info = new Driving(ORDINARY, enterDt,leavingDt,"","");
        service = new EtcDiscountService();
        actual = service.calculate(price, info);
        assertEquals(expected, actual);
    }

    /**
     * 休日割引
     */
    @Test
    void calculate_2() {
        var expected = 700;
        long price = 1000;

        // 休日割引
        LocalDateTime enterDt = LocalDateTime.of(2020,2,16,6,00);
        LocalDateTime leavingDt = LocalDateTime.of(2020,2,16, 9,00);
        Driving info = new Driving(ORDINARY, enterDt,leavingDt,"","");
        service = new EtcDiscountService();
        var actual = service.calculate(price, info);
        assertEquals(expected, actual);

        info.setType(KEI);
        service = new EtcDiscountService();
        actual = service.calculate(price, info);
        assertEquals(expected, actual);
    }

    /**
     * 深夜割引
     */
    @Test
    void calculate_3() {
        var expected = 700;
        long price = 1000;

        // 深夜割引　入場時刻
        LocalDateTime enterDt = LocalDateTime.of(2020, 2, 19, 0, 00);
        LocalDateTime leavingDt = LocalDateTime.of(2020, 2, 19, 4, 00);
        Driving info = new Driving(ORDINARY, enterDt, leavingDt, "", "");
        service = new EtcDiscountService();
        var actual = service.calculate(price, info);
        assertEquals(expected, actual);

        // 深夜割引　退場時刻
        enterDt = LocalDateTime.of(2020, 2, 18, 23, 59);
        leavingDt = LocalDateTime.of(2020, 2, 19, 3, 59);
        info = new Driving(ORDINARY, enterDt, leavingDt, "", "");
        service = new EtcDiscountService();
        actual = service.calculate(price, info);
        assertEquals(expected, actual);
    }

    /**
     * 該当なし
     */
    @Test
    void calculate_4() {
        var expected = 1000;
        long price = 1000;

        // 休日割引
        LocalDateTime enterDt = LocalDateTime.of(2020,2,18,12,00);
        LocalDateTime leavingDt = LocalDateTime.of(2020,2,18, 13,00);
        Driving info = new Driving(ORDINARY, enterDt,leavingDt,"","");
        service = new EtcDiscountService();
        var actual = service.calculate(price, info);
        assertEquals(expected, actual);

        info.setType(KEI);
        service = new EtcDiscountService();
        actual = service.calculate(price, info);
        assertEquals(expected, actual);
    }
}