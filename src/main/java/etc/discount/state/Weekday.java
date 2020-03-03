package etc.discount.state;

import etc.discount.Week;
import etc.discount.state.rule.CalendarState;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Weekday implements CalendarState {

    private Week week = new Week();
    private static CalendarState weekday = new Weekday();

    public Weekday() {

    }

    public static CalendarState getInstance () {
        return weekday;
    }

    @Override
    public void updateState(TimeMaster master, LocalDateTime eDate, LocalDateTime lDate) {
        // TODO 日マタギによって優先度が変わる可能性がある。
        DayOfWeek eWeek = eDate.getDayOfWeek();
        DayOfWeek lWeek = lDate.getDayOfWeek();
        if (!week.isWeekend(lDate) && !week.isWeekend(eDate)) {
            master.changeCalendarState(Holiday.getInstance());
        }
    }
}
