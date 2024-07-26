package org.apeiron.kernel.converters;

import java.time.OffsetDateTime;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 * Converts {@link OffsetDateTime} to {@link Date}
 * FIXME: Revisar en que casos nos puede ayudar esta clase
 */
public final class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {

    public static final OffsetDateTimeToDateConverter INSTANCE = new OffsetDateTimeToDateConverter();

    @Override
    public Date convert(OffsetDateTime source) {
        return source == null ? null : Date.from(source.toInstant());
    }
}
