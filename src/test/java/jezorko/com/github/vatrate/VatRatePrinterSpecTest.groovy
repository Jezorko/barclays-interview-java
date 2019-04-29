package jezorko.com.github.vatrate

import spock.lang.Specification
import spock.lang.Subject

import static jezorko.com.github.vatrate.TestUtils.nameAndRate

class VatRatePrinterSpecTest extends Specification {

    @Subject
    def vatRatePrinter = new VatRatePrinter()

    def "should output three highest and three lowest VAT values and countries"() {
        setup:
            def rates = (0..25).collect { nameAndRate("Test ${it}", new BigDecimal(it)) }

            def originalOutputStream = System.out
            def byteArrayOutputStream = new ByteArrayOutputStream()
            System.setOut(new PrintStream(byteArrayOutputStream))

        when:
            vatRatePrinter.printCurrentVatRates(rates)

        then:
            noExceptionThrown()
            "Highest VAT:\n" +
                    "\tTest 25: 25\n" +
                    "\tTest 24: 24\n" +
                    "\tTest 23: 23\n" +
                    "Lowest VAT:\n" +
                    "\tTest 2: 2\n" +
                    "\tTest 1: 1\n" +
                    "\tTest 0: 0" == byteArrayOutputStream.toString().strip()

        cleanup:
            System.setOut originalOutputStream
    }

    def "should throw if there are not enough VAT rates"() {
        when:
            vatRatePrinter.printCurrentVatRates([])

        then:
            thrown TooFewVatRatesException
    }

}
