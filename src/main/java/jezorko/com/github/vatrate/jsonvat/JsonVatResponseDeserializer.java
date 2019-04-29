package jezorko.com.github.vatrate.jsonvat;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import jezorko.com.github.vatrate.jsonvat.response.JsonVatResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

public class JsonVatResponseDeserializer {

    private final static String JSON_VAT_DATE_FORMAT = "yyyy-MM-dd";

    private final TypeAdapter<JsonVatResponse> gsonTypeAdapter = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
                                                                                  .setDateFormat(JSON_VAT_DATE_FORMAT)
                                                                                  .create()
                                                                                  .getAdapter(JsonVatResponse.class);

    JsonVatResponse deserialize(final @NotNull String responseAsJsonString) {
        try {
            return gsonTypeAdapter.fromJson(responseAsJsonString);
        } catch (IOException exception) {
            throw new ResponseDeserializationException(exception);
        }
    }

}
