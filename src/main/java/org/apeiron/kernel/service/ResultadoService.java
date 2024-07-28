package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.ResultadoDto;
import reactor.core.publisher.Mono;

public interface ResultadoService {
    /**
     * Save a Resultado.
     *
     * @param resultadoDto the entity to save.
     * @return the persisted entity.
     */
    Mono<ResultadoDto> save(ResultadoDto resultadoDto);

    /**
     * Regresa el resultado de una dictaminación
     *
     * @param id de la solicitud
     * @return ReporteDto.
     */
    Mono<ResultadoDto> findOne(String id);
}
