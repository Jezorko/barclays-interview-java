package jezorko.com.github.vatrate.jsonvat.response;

import lombok.Value;

import java.util.List;

@Value
public final class CountryVatRates {

    private final String name;
    private final List<VatPeriod> periods;

}
