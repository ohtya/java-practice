package cinema.ticket.model;

import lombok.Builder;
import lombok.Value;

import static cinema.ticket.model.VisitorType.*;

@Value
@Builder
public class Visitor {
    /**
     * 年齢
     * TODO Enumにする
     * FIXME: 恐らく、ほとんどのサービスが年齢ではなく生年月日を登録して頂いて、そこから計算していると思われる
     */
    int age;

    /**
     * {@link VisitorType} を返します
     *
     * @return {@link VisitorType}
     */
    public VisitorType type() {

        // 何らかの証拠を提示したいただく必要があるが、運用上、自己申告で通すケースがほとんどだと思われるため気にしない
        if (age <= 2) {
            return BABY;
        }

        if (age <= 6) {
            return TODDLER;
        }

        // FIXME: 本来は何らかの証拠を基に判断する必要がある
        if (age <= 12) {
            return PRIMARY_SCHOOL;
        }

        // FIXME: 本来は学生証を基に判断する必要がある
        if (age <= 15) {
            return JUNIOR_HIGH_SCHOOL;
        }

        // FIXME: 本来は学生証を基に判断する必要がある
        if (age <= 18) {
            return HIGH_SCHOOL;
        }

        if (syougaisya) {
            return HANDICAPPED;
        }

        if (isMember() && 60 <= age) {
            return CINEMA_CITIZEN_SENIOR;
        }

        // FIXME: 土日祝日の通常時間の場合のみ、シネマシティズンより安いため、ここを考慮して設計したほうが良い
        if (70 <= age) {
            return SENIOR;
        }

        if (isMember()) {
            return CINEMA_CITIZEN;
        }

        // FIXME: 本来は学生証を基に判断する必要がある
        if (age <= 20) {
            return VOCATIONAL;
        }

        // FIXME: 本来は学生証を基に判断する必要がある
        if (age <= 22) {
            return COLLEGE;
        }

        return GENERAL;
    }

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
        // FIXME: シネマシティズンにおけるシニアと通常のシニアで判定基準が異なる
        // FIXME: 通常のシニアを実装する際に検討する
        return 60 <= age;
    }
}
