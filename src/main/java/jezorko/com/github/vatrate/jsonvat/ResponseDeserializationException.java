package jezorko.com.github.vatrate.jsonvat;

import org.jetbrains.annotations.NotNull;

final class ResponseDeserializationException extends RuntimeException {

    ResponseDeserializationException(final @NotNull Throwable cause) {
        super("response deserialization failed", cause);
    }

}
