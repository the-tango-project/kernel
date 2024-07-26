package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.ResultadoDTO;
import reactor.core.publisher.Mono;

public interface ResultadoService {
    /**
     * Save a Resultado.
     *
     * @param ResultadoDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ResultadoDTO> save(ResultadoDTO resultadoDto);

    /**
     * Regresa el resultado de una dictaminación
     *
     * @param id de la solicitud
     * @return ReporteDTO.
     */
    Mono<ResultadoDTO> findOne(String id);
}
