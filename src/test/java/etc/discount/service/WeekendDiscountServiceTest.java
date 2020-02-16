package etc.discount.service;

import etc.discount.model.CarModel;
import etc.discount.model.DrivingInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 休日割引を実現するサービスのテスト.
 */
public class WeekendDiscountServiceTest {
    private static final WeekendDiscountService service = new WeekendDiscountService();

    @Test
    public void testCalculate() {
        // 車種が普通車、休日入場の場合
        LocalDateTime admissionAt = LocalDateTime.of(2020, 2, 9, 12, 00, 00);
        LocalDateTime exitAt = LocalDateTime.of(2020, 2, 9, 13, 00, 00);

        DrivingInfo info = new DrivingInfo(CarModel.ORDINARY, admissionAt, exitAt);
        long expected = 30;
        long actual = service.calculate(info);
        assertEquals(expected, actual);
    }
}