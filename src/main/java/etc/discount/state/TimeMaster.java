package etc.discount.state;

import etc.discount.state.rule.CalendarState;
import etc.discount.state.rule.DayState;

import java.time.LocalDateTime;

public class TimeMaster {
    private DayState dayState = Daytime.getInstance();
    private CalendarState calState = Weekday.getInstance();

    public TimeMaster(LocalDateTime enteringDate,
                      LocalDateTime leavingDate) {
        this.enteringDate = enteringDate;
        this.leavingDate = leavingDate;
        // TODO 日付関連がNullであることは考慮しない
        dayState.updateState(this, enteringDate.getHour(), leavingDate.getHour());
        calState.updateState(this, enteringDate, leavingDate);
    }
    // 入場時刻
    private LocalDateTime enteringDate;

    // 退場時刻
    private LocalDateTime leavingDate;

    public DayState getDayState() {
        return dayState;
    }

    public CalendarState getCalState() {
        return calState;
    }

    public void changeTimeState(DayState state) {
        this.dayState = state;
    }

    public void changeCalendarState(CalendarState state) {
        this.calState = state;

    }
}
