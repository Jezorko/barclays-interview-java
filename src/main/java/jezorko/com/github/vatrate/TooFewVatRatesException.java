package jezorko.com.github.vatrate;

final class TooFewVatRatesException extends RuntimeException {

    TooFewVatRatesException(final int minimum) {
        super("there are too few VAT rates present, expected at least " + minimum);
    }

}
