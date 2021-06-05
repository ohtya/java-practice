package cinema.ticket.domain;

/**
 * 上映日時
 */
public interface ScreenTime {

    /**
     * 上映日時がレイトショーに該当するか
     *
     * @return true: レイトショーである, false: レイトショーではない
     */
    boolean isLateShow();

    /**
     * 上映日時が休日に該当するか
     *
     * @return true: 休日である, false: 休日ではない
     */
    boolean isHoliday();

    /**
     * 上映日時が映画の日に該当するか
     *
     * @return true: 映画の日である, false: 映画の日ではない
     */
    boolean isMovieDay();
}
