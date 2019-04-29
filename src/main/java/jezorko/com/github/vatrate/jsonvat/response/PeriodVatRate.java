package jezorko.com.github.vatrate.jsonvat.response;

import lombok.Value;

import java.math.BigDecimal;

@Value
public final class PeriodVatRate {

    private final BigDecimal standard;

}