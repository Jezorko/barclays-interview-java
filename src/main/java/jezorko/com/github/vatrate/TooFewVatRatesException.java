package jezorko.com.github.vatrate;

public class TooFewVatRatesException extends RuntimeException {

    public TooFewVatRatesException(int minimum) {
        super("there are too few VAT rates present, expected at least " + minimum);
    }
}
