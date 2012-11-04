package us.bpsm.edn.joda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static us.bpsm.edn.parser.Parser.Config.EDN_INSTANT;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.junit.Test;

import us.bpsm.edn.parser.Parseable;
import us.bpsm.edn.parser.Parser;
import us.bpsm.edn.parser.Parsers;

public class InstantToJodaTest {

    private static long toMillis(int hours, int minutes) {
        return ((hours * 60L) + minutes) * 60L * 1000L;
    }

    @Test
    public void testToDateTime() {
        Parseable r = Parsers.newParseable(
                "#inst \"1987-06-05T04:03:02.123456789-03:00\"");
        Parser p = Parsers.newParser(Parsers.newParserConfigBuilder()
                .putTagHandler(EDN_INSTANT, new InstantToJodaDateTime())
                .build());
        DateTime d = (DateTime) p.nextValue(r);
        assertEquals(1987, d.getYear());
        assertEquals(6, d.getMonthOfYear());
        assertEquals(5, d.getDayOfMonth());
        assertEquals(4, d.getHourOfDay());
        assertEquals(3, d.getMinuteOfHour());
        assertEquals(2, d.getSecondOfMinute());
        assertEquals(123, d.getMillisOfSecond());
        assertTrue(d.getZone().isFixed());
        assertEquals(toMillis(-3,0), d.getZone().getOffset(d.getMillis()));
        assertEquals("1987-06-05T04:03:02.123-03:00", d.toString());
    }

    @Test
    public void testToDateTimeZulu() {
        Parseable r = Parsers.newParseable(
                "#inst \"1987-06-05T04:03:02.123456789+00:00\"");
        Parser p = Parsers.newParser(Parsers.newParserConfigBuilder()
                .putTagHandler(EDN_INSTANT, new InstantToJodaDateTime())
                .build());
        DateTime d = (DateTime) p.nextValue(r);
        assertEquals(1987, d.getYear());
        assertEquals(6, d.getMonthOfYear());
        assertEquals(5, d.getDayOfMonth());
        assertEquals(4, d.getHourOfDay());
        assertEquals(3, d.getMinuteOfHour());
        assertEquals(2, d.getSecondOfMinute());
        assertEquals(123, d.getMillisOfSecond());
        assertTrue(d.getZone().isFixed());
        assertEquals(toMillis(0,0), d.getZone().getOffset(d.getMillis()));
        assertEquals("1987-06-05T04:03:02.123Z", d.toString());
    }

    @Test
    public void testToInstant() {
        Parseable r = Parsers.newParseable(
                "#inst \"1987-06-05T04:03:02.123456789+03:00\"");
        Parser p = Parsers.newParser(Parsers.newParserConfigBuilder()
                .putTagHandler(EDN_INSTANT, new InstantToJodaInstant())
                .build());
        Instant d = (Instant) p.nextValue(r);
        assertEquals("1987-06-05T01:03:02.123Z", d.toString());
    }



}
