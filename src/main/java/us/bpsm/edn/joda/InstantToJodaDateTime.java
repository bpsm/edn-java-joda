package us.bpsm.edn.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import us.bpsm.edn.parser.AbstractInstantHandler;
import us.bpsm.edn.parser.ParsedInstant;

public final class InstantToJodaDateTime extends AbstractInstantHandler {

    private static final int NANOSECONDS_PER_MILLISECOND = 1000000;

    @Override
    protected Object transform(ParsedInstant pi) {
        return toDateTime(pi);
    }

    static DateTime toDateTime(ParsedInstant pi) {
        final int millis = pi.nanoseconds / NANOSECONDS_PER_MILLISECOND;
        final DateTimeZone tz = DateTimeZone.forOffsetHoursMinutes(
                pi.offsetSign * pi.offsetHours,
                pi.offsetSign * pi.offsetMinutes);
        final DateTime d = new DateTime(pi.years, pi.months, pi.days, pi.hours,
                pi.minutes, pi.seconds, millis, tz);
        return d;
    }

}
