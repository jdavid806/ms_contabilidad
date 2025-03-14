package utils;

import java.math.BigDecimal;

public class TaxUtils {
    public static BigDecimal calculateVAT(BigDecimal amount, BigDecimal vatRate) {
        return amount.multiply(vatRate).divide(BigDecimal.valueOf(100));
    }
}
