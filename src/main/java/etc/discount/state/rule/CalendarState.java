package etc.discount.state.rule;

import etc.discount.state.TimeMaster;

import java.time.LocalDateTime;

public interface CalendarState {
    public void updateState(TimeMaster master, LocalDateTime eDate, LocalDateTime lDate);

}
