package org.apeiron.kernel.service.dto.factories;

import org.apeiron.kernel.domain.enumeration.TipoMovimiento;
import org.apeiron.kernel.service.dto.BitacoraDto;
import org.apeiron.kernel.service.dto.ContextDto;

public class BitacoraFactory {

    private BitacoraFactory() {}

    public static BitacoraDto fromContext(ContextDto contexto) {
        return BitacoraDto
            .builder()
            .solicitudId(contexto.getSolicitud().getId())
            .accion(contexto.getAccion())
            .estadoInicial(contexto.getEstadoActual().getNombre())
            .estadoFinal(contexto.getTransicion().getDestino())
            .fechaCreacion(contexto.getInicioTransicion())
            .tipo(TipoMovimiento.TRANSICION_EXITOSA)
            .usuarioId(contexto.getUsuario())
            .descripcion(resolveDescripcion(contexto))
            .build();
    }

    private static String resolveDescripcion(ContextDto contexto) {
        return new StringBuilder()
            .append("Transicion éxitosa")
            .append(" ")
            .append("al ejecutar la acción ")
            .append(contexto.getAccion())
            .append(" ")
            .append("por el usuario ")
            .append(contexto.getUsuario())
            .append(" ")
            .append("la solicitud pasa del estado ")
            .append(contexto.getEstadoActual().getNombre())
            .append(" ")
            .append("al estado ")
            .append(contexto.getTransicion().getDestino())
            .toString();
    }
}
