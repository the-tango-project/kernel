package org.apeiron.kernel.service.dto.factories;

import java.time.Instant;
import org.apeiron.kernel.domain.enumeration.TipoComentario;
import org.apeiron.kernel.service.dto.ComentarioDto;
import org.apeiron.kernel.service.dto.ContextDto;

public class ComentarioFactory {

    private ComentarioFactory() {}

    public static ComentarioDto fromContext(ContextDto contexto) {
        return ComentarioDto
            .builder()
            .solicitudId(contexto.getSolicitud().getId())
            .text(contexto.getTransicionContext().getMensaje())
            .fechaCreacion(Instant.now())
            .tipo(TipoComentario.NOTIFICACION)
            .usuarioId(contexto.getUsuario())
            .build();
    }
}
