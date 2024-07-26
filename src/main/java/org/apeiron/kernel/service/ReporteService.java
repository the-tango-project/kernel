package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.ReporteDTO;
import reactor.core.publisher.Flux;

public interface ReporteService {
    /**
     * Genera un reporte con el número total de solicitudes que hay en cada estado
     * de una solucion
     *
     * @param solucionId the id of the solucion.
     * @return ReporteDTO.
     */
    Flux<ReporteDTO> countTotalSolicitudesByEstado(String solucionId);
}
