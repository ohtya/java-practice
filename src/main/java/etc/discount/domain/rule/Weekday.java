package etc.discount.domain.rule;

import etc.discount.Holiday;
import etc.discount.domain.DiscountRule;
import etc.discount.model.Drive;
import etc.discount.model.Route;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 平日朝夕割引
 * https://www.driveplaza.com/traffic/tolls_etc/etc_dis_weekday/
 * <p>
 * 平日とは、月曜日から金曜日までを指します
 * 但し、祝日は除外します
 * FIXME: 作成してください → 通知テスト
 */
public class Weekday implements DiscountRule {

    /**
     * 入り口料金所または出口料金所を平日の以下の時間帯に通過した場合に適用可能です
     * - 朝(6時から9時まで)
     * - 夕(17時から20時まで)
     * <p>
     * また、1か月の利用回数が5回以上の場合に適用可能です
     * 1か月は、1日から末日までとします
     * <p>
     * 対象区間は地方部のみです
     *
     * @return true: 可能, false: 不可
     */
    @Override
    public boolean isApplicable(final Drive drive) {

        // TODO: wiki に設定をまとめる
        // 設定 > show quick ～
        // 方針: とりあえず作る

        // 休日を new する
        Holiday holiday = new Holiday();

        // 休日の場合は後の処理を行わない
        if (holiday.isWeekend(drive.getAdmissionAt()) && holiday.isWeekend(drive.getExitAt())) {
            return false;
        }

        // 入口が含まれていて良いし、出口が含まれていても良い
        // FIXME: 朝夕まとめて考えないとうまくいかない？
        // 入場日時と退場日時が朝夕に含まれていない場合は後の処理を行わない
        if (!isIn(drive.getAdmissionAt()) && !isIn(drive.getExitAt())) {
            return false;
        }

        // 利用回数が5回未満の場合は後の処理を行わない
        if (drive.getCount() < 5) {
            return false;
        }

        // 対象区間
        if (!Objects.equals(Route.LOCAL, drive.getRoute())) {
            return false;
        }

        return true;
    }

    /**
     * 以下のルールで割引率を適用します
     * - 利用回数が5回から9回までの場合、還元率30%
     * - 利用回数が10回以上の場合、還元率50%
     *
     * @return 割引率(還元率)
     */
    @Override
    public long discountRate(final Drive drive) {
        if (10 <= drive.getCount()) {
            return 50;
        } else if (5 <= drive.getCount()) {
            return 30;
        }
        return 0;
    }

    // TODO: Drive にもたせたほうが良いのか？
    // 朝夕に含まれるか
    private boolean isIn(final LocalDateTime localDateTime) {
        // 朝 -> 6時から9時まで -> 06:00以上 - 09:00以下
        // 夕 -> 17時から20時まで -> 17:00以上 - 20:00以下
        return 6 <= localDateTime.getHour() && localDateTime.getHour() <= 9 || 17 <= localDateTime.getHour() && localDateTime.getHour() <= 20;
    }
}
