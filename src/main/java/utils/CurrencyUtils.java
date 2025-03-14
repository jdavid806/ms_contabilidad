package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {
public static String formatCurrency(BigDecimal amount, Locale locale) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(amount);
    }
}
