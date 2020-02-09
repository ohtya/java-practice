package etc.discount.service;

import etc.discount.model.CarModel;
import etc.discount.model.DrivingInfo;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 休日割引を実現するサービスのテスト.
 */
public class WeekendDiscountServiceTest {
    private static final WeekendDiscountService service = new WeekendDiscountService();

    @Test
    public void testCalculate() {
        // 車種が普通車、休日入場の場合
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date admissionTime = df.parse("2020/02/09 12:00:00");
            Date exitTime = df.parse("2020/02/09 13:00:00");

            DrivingInfo info = new DrivingInfo(CarModel.ORDINARY, admissionTime, exitTime);
            long expected = 30;
            long actual = service.calculate(info);
            assertEquals(expected, actual);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}