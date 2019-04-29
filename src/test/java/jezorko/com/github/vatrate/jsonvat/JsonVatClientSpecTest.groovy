package jezorko.com.github.vatrate.jsonvat

import jezorko.com.github.vatrate.jsonvat.response.JsonVatResponse
import spock.lang.Specification
import spock.lang.Subject

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

import static java.util.concurrent.CompletableFuture.completedFuture

class JsonVatClientSpecTest extends Specification {

    def httpClientMock = Mock HttpClient
    def responseDeserializerMock = Mock JsonVatResponseDeserializer

    @Subject
    def jsonVatClient = new JsonVatClient(httpClientMock, responseDeserializerMock)

    def 'should send a GET request and return a deserialized response'() {
        given:
            def testBody = 'test'
            def responseMock = Mock HttpResponse
            def expectedResult = new JsonVatResponse([])

        when:
            def actualResult = jsonVatClient.fetchJsonVatResponse()

        then:
            1 * httpClientMock.sendAsync({ HttpRequest it -> it.method() == 'GET' && it.uri() == JsonVatClient.JSON_VAT_URI },
                                         _ as HttpResponse.BodyHandler) >> completedFuture(responseMock)
            1 * responseMock.body() >> testBody
            1 * responseDeserializerMock.deserialize(testBody) >> expectedResult

        and:
            expectedResult.is actualResult.get()
    }

    def 'should rethrow any exceptions thrown by HTTP client'() {
        given:
            def expectedException = Mock Exception

        when:
            jsonVatClient.fetchJsonVatResponse()

        then:
            1 * httpClientMock.sendAsync(_, _) >> { throw expectedException }

        and:
            def actualException = thrown Exception
            expectedException == actualException
    }

}
