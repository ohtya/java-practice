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
     * TODO Enumにする
     */
    int visitorType;

    /**
     * 障碍者
     */
    boolean syougaisya;

    /**
     * 会員かどうか
     */
    boolean isKaiin;
}
