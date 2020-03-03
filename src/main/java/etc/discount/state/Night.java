package etc.discount.state;

import etc.discount.state.rule.DayState;

public class Night implements DayState {

    private static DayState night = new Night();

    public Night() {

    }

    public static DayState getInstance() {
        return night;
    }

    @Override
    public void updateState(TimeMaster master, Integer eHour, Integer lHour) {
        if ( (6 <= eHour && eHour < 9) || (6 <= lHour && lHour < 9)  ) {
            master.changeTimeState(Daytime.getInstance());
        }
    }
}
