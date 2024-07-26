package org.apeiron.kernel.converters;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 * Converts {@link Date} to {@link OffsetDateTime}
 * FIXME: Revisar en que casos nos puede ayudar esta clase
 */
public class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {

    public static final DateToOffsetDateTimeConverter INSTANCE = new DateToOffsetDateTimeConverter();

    @Override
    public OffsetDateTime convert(Date source) {
        return source == null ? null : source.toInstant().atOffset(ZoneOffset.UTC);
    }
}
