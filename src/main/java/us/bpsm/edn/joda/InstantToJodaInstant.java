package us.bpsm.edn.joda;

import us.bpsm.edn.parser.AbstractInstantHandler;
import us.bpsm.edn.parser.ParsedInstant;

public final class InstantToJodaInstant extends AbstractInstantHandler {

    @Override
    protected Object transform(ParsedInstant pi) {
        return InstantToJodaDateTime.toDateTime(pi).toInstant();
    }

}
