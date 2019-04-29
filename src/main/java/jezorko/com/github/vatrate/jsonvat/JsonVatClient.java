package jezorko.com.github.vatrate.jsonvat;

import jezorko.com.github.vatrate.jsonvat.response.JsonVatResponse;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class JsonVatClient {

    private final static HttpResponse.BodyHandler<String> STRING_BODY_HANDLER = HttpResponse.BodyHandlers.ofString();
    private final static URI JSON_VAT_URI = URI.create("http://jsonvat.com/");

    private final HttpClient httpClient;
    private final JsonVatResponseDeserializer responseDeserializer;

    public final CompletableFuture<JsonVatResponse> fetchJsonVatResponse() {
        return httpClient.sendAsync(createRequest(), STRING_BODY_HANDLER)
                         .thenApply(HttpResponse::body)
                         .thenApply(responseDeserializer::deserialize);
    }

    private HttpRequest createRequest() {
        return HttpRequest.newBuilder()
                          .uri(JSON_VAT_URI)
                          .build();
    }

}
