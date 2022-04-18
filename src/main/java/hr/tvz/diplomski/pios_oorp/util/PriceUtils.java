package hr.tvz.diplomski.pios_oorp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtils {

    /**
     * Calculates discount percentage between two prices, scaled to 2 decimal places
     * @param regularPrice regular price of {@link hr.tvz.diplomski.pios_oorp.domain.Product}
     * @param actionPrice action price of {@link hr.tvz.diplomski.pios_oorp.domain.Product}
     * @return discount percentage scaled to 2 decimal places
     */
    public BigDecimal calculateDiscountPercentage(BigDecimal regularPrice, BigDecimal actionPrice) {
        if (actionPrice == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.ONE.subtract(actionPrice.divide(regularPrice, 4, RoundingMode.HALF_UP))
                .multiply(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
