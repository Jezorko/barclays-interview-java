package jezorko.com.github.vatrate

import jezorko.com.github.vatrate.jsonvat.response.CountryVatRates
import jezorko.com.github.vatrate.jsonvat.response.PeriodVatRate
import jezorko.com.github.vatrate.jsonvat.response.VatPeriod

import java.text.SimpleDateFormat
import java.time.LocalDateTime

class TestUtils {

    static nameAndRate(name, rate) {
        return new CountryNameAndCurrentStandardVatRate(name, rate)
    }

    static countryRates(name, value) {
        return new CountryVatRates(name, [vatPeriod(0, value)])
    }

    static countryRates(name, int dateOffset, value) {
        return new CountryVatRates(name, [vatPeriod(dateOffset, value)])
    }

    static countryRates(name, int firstDateOffset, firstValue, int secondDateOffset, secondValue) {
        return new CountryVatRates(name, [
                vatPeriod(firstDateOffset, firstValue),
                vatPeriod(secondDateOffset, secondValue)
        ])
    }

    static def vatPeriod(String dateAsString, value) {
        return new VatPeriod(dateFromString(dateAsString), new PeriodVatRate(value))
    }

    static vatPeriod(int dateOffset, value) {
        return new VatPeriod(addDaysToToday(dateOffset), new PeriodVatRate(value))
    }

    static addDaysToToday(int daysOffset) {
        return LocalDateTime.now().plusDays(daysOffset).toDate()
    }

    private static def dateFromString(dateAsString) {
        return new SimpleDateFormat('yyyy-MM-dd').parse(dateAsString as String)
    }

}
