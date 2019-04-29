package jezorko.com.github.vatrate.jsonvat.response;

import lombok.Value;

import java.util.Date;

@Value
public final class VatPeriod {

    private final Date effectiveFrom;
    private final PeriodVatRate rates;

}