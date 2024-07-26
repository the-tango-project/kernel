package org.apeiron.kernel.service.impl;

import static org.apeiron.kernel.service.util.QueryHelper.buildSolucionQuery;

import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.domain.HistoricoSolucion;
import org.apeiron.kernel.domain.Solucion;
import org.apeiron.kernel.domain.enumeration.EstadoSolucion;
import org.apeiron.kernel.repository.DefinicionEvaluacionRepository;
import org.apeiron.kernel.repository.HistoricoSolucionRepository;
import org.apeiron.kernel.repository.SolucionRepository;
import org.apeiron.kernel.service.SolucionService;
import org.apeiron.kernel.service.dto.DefinicionEvaluacionDTO;
import org.apeiron.kernel.service.dto.SolucionDTO;
import org.apeiron.kernel.service.dto.factories.HistoricoFactory;
import org.apeiron.kernel.service.mapper.DefinicionEvaluacionMapper;
import org.apeiron.kernel.service.mapper.SolucionMapper;
import org.apeiron.kernel.service.util.Filtro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Solucion}.
 */
@Service
@RequiredArgsConstructor
public class SolucionServiceImpl implements SolucionService {

    private final Logger log = LoggerFactory.getLogger(SolucionServiceImpl.class);

    private final SolucionRepository solucionRepository;
    private final HistoricoSolucionRepository historicoRepository;

    private final SolucionMapper solucionMapper;

    private final DefinicionEvaluacionMapper definicionEvaluacionMapper;
    private final DefinicionEvaluacionRepository definicionEvaluacionRepository;

    @Override
    public Mono<SolucionDTO> save(SolucionDTO solucionDTO) {
        log.debug("Request to save Solucion : {}", solucionDTO);
        return solucionRepository
            .save(solucionMapper.toEntity(solucionDTO))
            .map(HistoricoFactory::toHistorico)
            .flatMap(historicoRepository::save)
            .map(HistoricoSolucion::getSolucion)
            .map(solucionMapper::toDto);
    }

    @Override
    public Mono<SolucionDTO> create(SolucionDTO solucionDTO) {
        solucionDTO.setVersion(0);
        solucionDTO.setEstado(EstadoSolucion.EN_CAPTURA);
        return this.save(solucionDTO);
    }

    @Override
    public Mono<SolucionDTO> publicar(SolucionDTO solucionDTO) {
        solucionDTO.setVersion(solucionDTO.getVersion() + 1);
        solucionDTO.setEstado(EstadoSolucion.PUBLICADA);
        return this.save(solucionDTO);
    }

    @Override
    public Mono<SolucionDTO> archivar(SolucionDTO solucionDTO) {
        solucionDTO.setEstado(EstadoSolucion.ARCHIVADA);
        return this.save(solucionDTO);
    }

    @Override
    public Mono<SolucionDTO> update(SolucionDTO solucionDTO) {
        Solucion solucion = solucionMapper.toEntity(solucionDTO);
        log.debug("Request to update Solucion : {}", solucionDTO);
        return historicoRepository
            .save(HistoricoFactory.toHistorico(solucion))
            .map(HistoricoSolucion::getSolucion)
            .map(solucionMapper::toDto);
    }

    @Override
    public Mono<SolucionDTO> partialUpdate(SolucionDTO solucionDTO) {
        log.debug("Request to partially update Solucion : {}", solucionDTO);

        return solucionRepository
            .findById(solucionDTO.getId())
            .map(existingSolucion -> {
                solucionMapper.partialUpdate(existingSolucion, solucionDTO);

                return existingSolucion;
            })
            .flatMap(solucionRepository::save)
            .map(solucionMapper::toDto);
    }

    @Override
    public Flux<SolucionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Solucions");
        return solucionRepository.findAllBy(pageable).map(solucionMapper::toDto);
    }

    @Override
    public Flux<SolucionDTO> findAll(Filtro filtro) {
        log.debug("Request to get all Solucions by filtro");
        return solucionRepository.findAll(buildSolucionQuery(filtro)).map(solucionMapper::toDto);
    }

    /**
     * Método interno para consulta entities de
     * {@link org.apeiron.kernel.domain.Solucion}
     *
     * @param filtro
     * @param pageable
     * @return Flux<{@link org.apeiron.kernel.domain.Solucion}>
     */
    private Flux<Solucion> findAllEntities(Filtro filtro, Pageable pageable) {
        log.debug("Request to get all Solucions");
        return solucionRepository
            .findAll(buildSolucionQuery(filtro), pageable.getSort())
            .skip(pageable.getOffset())
            .take(pageable.getPageSize());
    }

    @Override
    public Flux<SolucionDTO> findAll(Filtro filtro, Pageable pageable) {
        log.debug("Request to get all SolucionDTO");
        return findAllEntities(filtro, pageable).map(solucionMapper::toDto);
    }

    @Override
    public Flux<SolucionDTO> findAllByEstado(String estado, Pageable pageable) {
        log.debug("Request to get all Solucions by PUBLICADA");
        return solucionRepository.findAllByEstado("PUBLICADA", pageable).map(solucionMapper::toDto);
    }

    public Mono<Long> countAll() {
        return solucionRepository.count();
    }

    public Mono<Long> countAll(Filtro filtro) {
        log.debug("Count all with filtro");
        return solucionRepository.count(buildSolucionQuery(filtro));
    }

    @Override
    public Mono<SolucionDTO> findOne(String id) {
        log.debug("Request to get Solucion : {}", id);
        return solucionRepository.findById(id).map(solucionMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Solucion : {}", id);
        return solucionRepository.deleteById(id);
    }

    @Override
    public Mono<SolucionDTO> findOneByLastVersion(String id) {
        log.debug("Request to get Solucion : {}", id);
        return solucionRepository
            .findById(id)
            .map(HistoricoFactory::toHistorico)
            .flatMap(historico -> historicoRepository.findById(historico.getId()).switchIfEmpty(Mono.just(historico)))
            .map(HistoricoSolucion::getSolucion)
            .map(solucionMapper::toDto);
    }

    @Override
    public Mono<DefinicionEvaluacionDTO> findOneDefinicionEvaluacion(String solucionId) {
        return definicionEvaluacionRepository.findById(solucionId).map(definicionEvaluacionMapper::toDto);
    }

    @Override
    public Mono<DefinicionEvaluacionDTO> saveDefinicionEvaluacion(DefinicionEvaluacionDTO definicionDTO) {
        return definicionEvaluacionRepository
            .save(definicionEvaluacionMapper.toEntity(definicionDTO))
            .map(definicionEvaluacionMapper::toDto);
    }
}
