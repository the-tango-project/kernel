package org.apeiron.kernel.service.dto.factories;

import java.time.Instant;
import org.apeiron.kernel.domain.enumeration.TipoComentario;
import org.apeiron.kernel.service.dto.ComentarioDTO;
import org.apeiron.kernel.service.dto.ContextDTO;

public class ComentarioFactory {

    private ComentarioFactory() {}

    public static ComentarioDTO fromContext(ContextDTO contexto) {
        return ComentarioDTO
            .builder()
            .solicitudId(contexto.getSolicitud().getId())
            .text(contexto.getTransicionContext().getMensaje())
            .fechaCreacion(Instant.now())
            .tipo(TipoComentario.NOTIFICACION)
            .usuarioId(contexto.getUsuario())
            .build();
    }
}
