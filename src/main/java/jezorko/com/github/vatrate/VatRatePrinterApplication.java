package jezorko.com.github.vatrate;

import jezorko.com.github.vatrate.jsonvat.JsonVatClient;
import jezorko.com.github.vatrate.jsonvat.JsonVatResponseDeserializer;
import lombok.RequiredArgsConstructor;

import java.net.http.HttpClient;

@RequiredArgsConstructor
public class VatRatePrinterApplication {

    public static void main(String... args) {
        final var jsonVatClient = new JsonVatClient(HttpClient.newHttpClient(), new JsonVatResponseDeserializer());
        final var currentVatValuesFinder = new CurrentVatRatesFinder();
        final var vatRatePrinter = new VatRatePrinter();

        jsonVatClient.fetchJsonVatResponse()
                     .thenApply(currentVatValuesFinder::findCurrentVatValues)
                     .thenAccept(vatRatePrinter::printCurrentVatRates)
                     .join();
    }

}
