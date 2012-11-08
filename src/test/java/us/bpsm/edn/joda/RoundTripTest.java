package us.bpsm.edn.joda;

import static org.junit.Assert.assertEquals;
import static us.bpsm.edn.parser.Parsers.newParser;
import static us.bpsm.edn.parser.Parsers.newParserConfigBuilder;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.junit.Test;

import us.bpsm.edn.parser.Parseable;
import us.bpsm.edn.parser.Parser;
import us.bpsm.edn.parser.Parsers;
import us.bpsm.edn.printer.Printer;
import us.bpsm.edn.printer.Printers;
import us.bpsm.edn.protocols.Protocol;

public class RoundTripTest {

    @Test
    public void testDateTimes() {
        for (int i = 0; i < N_REPS; i++) {
            DateTime r1 = randomDateTime();
            String s1 = printEdn(r1);
            DateTime r2 = parseDateTime(s1);
            String s2 = printEdn(r2);
            DateTime r3 = parseDateTime(s2);
            assertEquals(r1, r2);
            assertEquals(s1, s2);
            assertEquals(r2, r3);
        }
    }

    @Test
    public void testInstants() {
        for (int i = 0; i < N_REPS; i++) {
            Instant r1 = randomInstant();
            String s1 = printEdn(r1);
            Instant r2 = parseInstant(s1);
            String s2 = printEdn(r2);
            Instant r3 = parseInstant(s2);
            assertEquals(r1, r2);
            assertEquals(s1, s2);
            assertEquals(r2, r3);
        }
    }

    private static int N_REPS = 1000;

    private static final Protocol<Printer.Fn<?>> PRINT_CFG =
            Printers.defaultProtocolBuilder()
            .put(Instant.class, new PrintJodaInstantFn())
            .put(DateTime.class, new PrintJodaDateTimeFn())
            .build();

    private static final Parser PARSE_INST_AS_DATE_TIME =
            newParser(newParserConfigBuilder()
            .putTagHandler(Parser.Config.EDN_INSTANT, new InstantToJodaDateTime())
            .build());

    private static final Parser PARSE_INST_AS_INSTANT =
            newParser(newParserConfigBuilder()
            .putTagHandler(Parser.Config.EDN_INSTANT, new InstantToJodaInstant())
            .build());

    private static DateTime parseDateTime(String edn) {
        Parseable r = Parsers.newParseable(edn);
        return (DateTime) PARSE_INST_AS_DATE_TIME.nextValue(r);
    }

    private static Instant parseInstant(String edn) {
        Parseable r = Parsers.newParseable(edn);
        return (Instant) PARSE_INST_AS_INSTANT.nextValue(r);
    }

    private static String printEdn(Object someInst) {
        StringBuilder sb = new StringBuilder();
        Printer printer = Printers.newPrinter(PRINT_CFG, sb);
        printer.printValue(someInst);
        printer.close();
        return sb.toString();
    }

    private static Instant randomInstant() {
        long offset = (long) (Math.random()*Integer.MAX_VALUE);
        return new Instant(offset);
    }

    private static DateTime randomDateTime() {
        long offset = (long) (Math.random()*Integer.MAX_VALUE);
        int h = ((int)(Math.random()*25)) - 12;
        return new DateTime(offset, DateTimeZone.forOffsetHours(h));
    }

}
