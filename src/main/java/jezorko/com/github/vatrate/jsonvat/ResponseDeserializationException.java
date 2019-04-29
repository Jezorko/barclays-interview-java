package jezorko.com.github.vatrate.jsonvat;

public final class ResponseDeserializationException extends RuntimeException {

    public ResponseDeserializationException(Throwable cause) {
        super("response deserialization failed", cause);
    }

}
