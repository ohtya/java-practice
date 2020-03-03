package etc.discount;

import etc.discount.model.CarModel;
import etc.discount.model.Route;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO とりあえずで作ったので適当なクラス名になったから適切な名称にする
public class Week {

    protected static final List<DayOfWeek> WEEKEND = Arrays
            .stream(DayOfWeek.values())
            .filter(week -> week.equals(DayOfWeek.SATURDAY) || week.equals(DayOfWeek.SUNDAY))
            .collect(Collectors.toList());

    // TODO 突貫工事でここに置く
    public static final List<CarModel> APPLICABLE_CAR_MODEL =
            Arrays.stream(CarModel.values())
                    .filter(model -> model.equals(CarModel.KEI) || model.equals(CarModel.ORDINARY))
                    .collect(Collectors.toList());

    public boolean isWeekend(final LocalDateTime date) {
        if (date == null) {
            // TODO パクリなのでとりあえずThrowはなし。
        }
        return WEEKEND.contains(date.getDayOfWeek());
    }

    public boolean isApplicable(CarModel model) {
        return APPLICABLE_CAR_MODEL.contains(model);
    }
}
