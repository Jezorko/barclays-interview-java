package jezorko.com.github.vatrate;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

class VatRatePrinter {

    private final static int REQUIRED_MINIMUM_VAT_RATES = 6;

    final void printCurrentVatRates(List<CountryNameAndCurrentStandardVatRate> currentVatRates) {
        if (currentVatRates.size() < REQUIRED_MINIMUM_VAT_RATES) {
            throw new TooFewVatRatesException(REQUIRED_MINIMUM_VAT_RATES);
        }

        final var sortedCurrentVatValues = currentVatRates.stream()
                                                          .sorted(comparing(CountryNameAndCurrentStandardVatRate::getTodayActualVatRate))
                                                          .collect(toList());

        System.out.println("Highest VAT:");
        printVatRate(sortedCurrentVatValues.get(sortedCurrentVatValues.size() - 1));
        printVatRate(sortedCurrentVatValues.get(sortedCurrentVatValues.size() - 2));
        printVatRate(sortedCurrentVatValues.get(sortedCurrentVatValues.size() - 3));

        System.out.println("Lowest VAT:");
        printVatRate(sortedCurrentVatValues.get(2));
        printVatRate(sortedCurrentVatValues.get(1));
        printVatRate(sortedCurrentVatValues.get(0));
    }

    private void printVatRate(CountryNameAndCurrentStandardVatRate vatRate) {
        System.out.println("\t" + vatRate.getCountryName() + ": " + vatRate.getTodayActualVatRate());
    }

}
