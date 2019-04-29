package jezorko.com.github.vatrate;

final class MissingVatPeriodsException extends RuntimeException {

    MissingVatPeriodsException() {
        super("vat periods are missing, cannot find the most recent");
    }
}
