package etc.discount.service;

import etc.discount.domain.DiscountRule;
import etc.discount.domain.rule.Night;
import etc.discount.domain.rule.Weekday;
import etc.discount.domain.rule.Weekend;
import etc.discount.model.Drive;

import java.util.List;

/**
 * ETC割引を実現するサービス.
 */
public class EtcDiscountService implements DiscountService {

    private final List<DiscountRule> discountRules = List.of(new Night(), new Weekend(), new Weekday());

    @Override
    public long calculate(final Drive drive) {
        return discountRules.stream().filter(
            discountRule -> discountRule.isApplicable(drive)
        ).map(
            discountRule -> discountRule.discountRate(drive)
        ).findFirst().orElse(0L);
    }

    @Override
    public long add(long x, long y) {
        return x + y;
    }
}
