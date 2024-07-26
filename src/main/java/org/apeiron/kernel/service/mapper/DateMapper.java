package org.apeiron.kernel.service.mapper;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.apeiron.kernel.service.exception.GeneralException;
import org.mapstruct.Mapper;

/**
 * Mapper para fechas de Instant a OffsetDateTime
 * @see Instant
 * @see OffsetDateTime
 */
@Mapper(componentModel = "spring")
public class DateMapper {

    /**
     * Mapea de Instant a OffsetDateTime.
     *
     * @param date fecha original
     * @return fecha mapeada
     */
    public OffsetDateTime asOffsetDateTime(Instant date) {
        try {
            return date != null ? date.atOffset(ZoneOffset.UTC) : null;
        } catch (DateTimeException ex) {
            throw new GeneralException("Error al traducir fecha", ex);
        }
    }

    /**
     * Mapea de OffsetDateTime a Instant.
     *
     * @param date fecha original
     * @return fecha mapeada
     */
    public Instant asInstant(OffsetDateTime date) {
        try {
            return date != null ? date.toInstant() : null;
        } catch (UnsupportedOperationException ex) {
            throw new GeneralException("Error al traducir fecha", ex);
        }
    }
}
