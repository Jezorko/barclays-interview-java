package jezorko.com.github.vatrate.jsonvat.response;

import lombok.Value;

import java.util.List;

@Value
public final class JsonVatResponse {

    private final List<CountryVatRates> rates;

}
