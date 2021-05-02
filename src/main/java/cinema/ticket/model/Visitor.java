package cinema.ticket.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Visitor {
    /**
     * 年齢
     * TODO Enumにする
     */
    int age;

    /**
     * 学生
     */
    VisitorType visitorType;

    /**
     * 障碍者
     */
    boolean syougaisya;

    /**
     * 会員かどうか
     */
    boolean isKaiin;

    /**
     * シネマシティズン会員か
     *
     * @return true: シネマシティズン会員である, false: シネマシティズン会員ではない
     */
    public boolean isMember() {
        // 会員フラグを基に判定します
        return isKaiin;
    }

    /**
     * シニアか
     *
     * @return true: シニアである, false: シニアではない
     */
    public boolean isSenior() {
        // 60歳以上の方をシニアとして扱います
        return 60 <= age;
    }
}
