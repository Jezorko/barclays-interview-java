package jezorko.com.github.vatrate

import jezorko.com.github.vatrate.jsonvat.response.CountryVatRates
import jezorko.com.github.vatrate.jsonvat.response.JsonVatResponse
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static jezorko.com.github.vatrate.TestUtils.countryRates
import static jezorko.com.github.vatrate.TestUtils.nameAndRate

class CurrentVatRatesFinderSpecTest extends Specification {

    @Subject
    def currentVatValuesFinder = new CurrentVatRatesFinder()

    @Unroll
    def 'should find #expectedResult in #givenVatRates'() {
        when:
            def actualResult = currentVatValuesFinder.findCurrentVatValues(givenVatRates)

        then:
            expectedResult == actualResult

        where:
            givenVatRates                                                                                           | expectedResult
            new JsonVatResponse([])                                                                                 | []
            new JsonVatResponse([countryRates('test', 0, 10G)])                                                     | [nameAndRate('test', 10G)]
            new JsonVatResponse([countryRates('test', 0, 10G, 10, 15G)])                                            | [nameAndRate('test', 15G)]
            new JsonVatResponse([countryRates('test', 0, 10G, -10, 15G)])                                           | [nameAndRate('test', 10G)]
            new JsonVatResponse([countryRates('first', 0, 10G, 10, 15G), countryRates('second', 0, 10G, -10, 15G)]) | [nameAndRate('first', 15G), nameAndRate('second', 10G)]
    }

    def 'should throw if vat rates are missing'() {
        when:
            currentVatValuesFinder.findCurrentVatValues(new JsonVatResponse([new CountryVatRates('test', [])]))

        then:
            thrown Exception
    }

}
