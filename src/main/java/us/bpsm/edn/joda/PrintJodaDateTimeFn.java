package us.bpsm.edn.joda;

import org.joda.time.DateTime;

import us.bpsm.edn.parser.Parser;
import us.bpsm.edn.printer.PrintFn;
import us.bpsm.edn.printer.Printer;

public class PrintJodaDateTimeFn extends PrintFn<DateTime> {

    @Override
    protected void eval(DateTime self, Printer printer) {
        String s = self.toString();
        if (s.endsWith("Z")) {
            s = s.substring(0, s.length()-1) + "-00:00";
        }
        printer.printValue(Parser.Config.EDN_INSTANT);
        printer.softspace();
        printer.printValue(s);

    }

}
