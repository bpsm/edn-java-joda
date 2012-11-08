package us.bpsm.edn.joda;

import org.joda.time.Instant;

import us.bpsm.edn.parser.Parser;
import us.bpsm.edn.printer.Printer;

public class PrintJodaInstantFn implements Printer.Fn<Instant> {

    @Override
    public void eval(Instant self, Printer printer) {
        String s = self.toString();
        assert s.endsWith("Z");
        s = s.substring(0, s.length()-1) + "-00:00";
        printer.printValue(Parser.Config.EDN_INSTANT);
        printer.softspace();
        printer.printValue(s);
    }

}
