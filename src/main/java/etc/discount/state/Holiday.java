package etc.discount.state;

import etc.discount.Week;
import etc.discount.model.CarModel;
import etc.discount.state.rule.CalendarState;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Holiday implements CalendarState {

    private static CalendarState holiday = new Holiday();
    private Week week;

    public Holiday() {

    }

    public static CalendarState getInstance() {
        return holiday;
    }

    @Override
    public void updateState(TimeMaster master, LocalDateTime eDate, LocalDateTime lDate) {
        // TODO 日マタギによって優先度が変わる可能性がある。
        DayOfWeek eWeek = eDate.getDayOfWeek();
        DayOfWeek lWeek = lDate.getDayOfWeek();
        if (week.isWeekend(lDate) || week.isWeekend(eDate)) {
            master.changeCalendarState(Weekday.getInstance());
        }
    }
}
