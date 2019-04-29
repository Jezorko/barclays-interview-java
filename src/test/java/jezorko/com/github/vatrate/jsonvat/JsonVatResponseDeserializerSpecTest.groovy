package jezorko.com.github.vatrate.jsonvat

import jezorko.com.github.vatrate.jsonvat.response.CountryVatRates
import jezorko.com.github.vatrate.jsonvat.response.JsonVatResponse
import jezorko.com.github.vatrate.jsonvat.response.PeriodVatRate
import jezorko.com.github.vatrate.jsonvat.response.VatPeriod
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.text.SimpleDateFormat

class JsonVatResponseDeserializerSpecTest extends Specification {

    @Subject
    def deserializer = new JsonVatResponseDeserializer()

    @Unroll
    "should deserialize #responseAsString and return #expectedResult"() {
        when:
            def actualResult = deserializer.deserialize responseAsString

        then:
            expectedResult == actualResult

        where:
            responseAsString                                                                                   | expectedResult
            '{"rates":[]}'                                                                                     | new JsonVatResponse([])
            '{"rates":[], "ignored_field":"test"}'                                                             | new JsonVatResponse([])
            '{"rates":[{"name":"Spain","periods":[]}]}'                                                        | new JsonVatResponse([new CountryVatRates("Spain", [])])
            '{"rates":[{"name":"Spain","periods":[{"effective_from":"0000-01-01","rates":{"standard":21}}]}]}' | new JsonVatResponse([
                    new CountryVatRates("Spain", [vatPeriod('0000-01-01', 21G)])
            ])
    }

    @Unroll
    'should throw when deserialization fails for #invalidResponseAsString'() {
        when:
            deserializer.deserialize invalidResponseAsString

        then:
            thrown Exception

        where:
            invalidResponseAsString << ['', 'false']
    }

    def vatPeriod(dateAsString, standard) {
        return new VatPeriod(dateFromString(dateAsString), new PeriodVatRate(standard))
    }

    def dateFromString(dateAsString) {
        return new SimpleDateFormat('yyyy-MM-dd').parse(dateAsString as String)
    }

}
