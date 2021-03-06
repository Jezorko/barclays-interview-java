package jezorko.com.github.vatrate;

import jezorko.com.github.vatrate.jsonvat.response.JsonVatResponse;
import jezorko.com.github.vatrate.jsonvat.response.VatPeriod;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

class CurrentVatRatesFinder {

    @NotNull
    final List<CountryNameAndCurrentStandardVatRate> findCurrentVatValues(final @NotNull JsonVatResponse jsonVatResponse) {
        return jsonVatResponse.getRates()
                              .stream()
                              .map(rate -> rate.getPeriods()
                                               .stream()
                                               .max(comparing(VatPeriod::getEffectiveFrom))
                                               .map(VatPeriod::getRates)
                                               .map(todayRate -> new CountryNameAndCurrentStandardVatRate(rate.getName(), todayRate.getStandard()))
                                               .orElseThrow(MissingVatPeriodsException::new))
                              .collect(toList());
    }

}
