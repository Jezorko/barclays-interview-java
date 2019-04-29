package jezorko.com.github.vatrate.jsonvat

import jezorko.com.github.vatrate.jsonvat.response.CountryVatRates
import jezorko.com.github.vatrate.jsonvat.response.JsonVatResponse
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static jezorko.com.github.vatrate.TestUtils.vatPeriod

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

}
