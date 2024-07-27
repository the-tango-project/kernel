package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.ReporteDto;
import reactor.core.publisher.Flux;

public interface ReporteService {
    /**
     * Genera un reporte con el número total de solicitudes que hay en cada estado
     * de una solucion
     *
     * @param solucionId the id of the solucion.
     * @return ReporteDto.
     */
    Flux<ReporteDto> countTotalSolicitudesByEstado(String solucionId);
}
