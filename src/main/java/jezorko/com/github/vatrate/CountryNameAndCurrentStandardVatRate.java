package jezorko.com.github.vatrate;

import lombok.Value;

import java.math.BigDecimal;

@Value
final class CountryNameAndCurrentStandardVatRate {

    private final String countryName;
    private final BigDecimal todayActualVatRate;

}
