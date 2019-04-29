package jezorko.com.github.vatrate

import spock.lang.Specification

class VatRatePrinterSpecTest extends Specification {

    def "should output 'Hello World!' on the terminal and exit"() {
        setup:
            def originalOutputStream = System.out
            def byteArrayOutputStream = new ByteArrayOutputStream()
            System.setOut(new PrintStream(byteArrayOutputStream))

        when:
            VatRatePrinter.main()

        then:
            noExceptionThrown()
            "Hello World!" == byteArrayOutputStream.toString().strip()

        cleanup:
            System.setOut originalOutputStream
    }

}
