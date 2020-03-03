package etc.discount.state.rule;

import etc.discount.state.TimeMaster;

public interface DayState {
    // public DayState changeState(DayState state, Integer hour);
    public void updateState(TimeMaster master, Integer eHour, Integer lHour);
}
