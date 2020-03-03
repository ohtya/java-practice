package etc.discount.state;

import etc.discount.state.rule.DayState;

public class Daytime implements DayState {

    private static DayState daytime = new Daytime();

    public Daytime () {

    }

    public static DayState getInstance() {
        return daytime;
    }

    @Override
    public void updateState(TimeMaster master, Integer eHour, Integer lHour) {
        if ( (17 <= eHour && eHour < 20) || (17 <= lHour && lHour < 20)  ) {
            master.changeTimeState(Night.getInstance());
        }
    }
}
