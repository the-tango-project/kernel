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
import org.apeiron.kernel.service.dto.DefinicionEvaluacionDto;
import org.apeiron.kernel.service.dto.SolucionDto;
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
    public Mono<SolucionDto> save(SolucionDto solucionDto) {
        log.debug("Request to save Solucion : {}", solucionDto);
        return solucionRepository
            .save(solucionMapper.toEntity(solucionDto))
            .map(HistoricoFactory::toHistorico)
            .flatMap(historicoRepository::save)
            .map(HistoricoSolucion::getSolucion)
            .map(solucionMapper::toDto);
    }

    @Override
    public Mono<SolucionDto> create(SolucionDto solucionDto) {
        solucionDto.setVersion(0);
        solucionDto.setEstado(EstadoSolucion.EN_CAPTURA);
        return this.save(solucionDto);
    }

    @Override
    public Mono<SolucionDto> publicar(SolucionDto solucionDto) {
        solucionDto.setVersion(solucionDto.getVersion() + 1);
        solucionDto.setEstado(EstadoSolucion.PUBLICADA);
        return this.save(solucionDto);
    }

    @Override
    public Mono<SolucionDto> archivar(SolucionDto solucionDto) {
        solucionDto.setEstado(EstadoSolucion.ARCHIVADA);
        return this.save(solucionDto);
    }

    @Override
    public Mono<SolucionDto> update(SolucionDto solucionDto) {
        Solucion solucion = solucionMapper.toEntity(solucionDto);
        log.debug("Request to update Solucion : {}", solucionDto);
        return historicoRepository
            .save(HistoricoFactory.toHistorico(solucion))
            .map(HistoricoSolucion::getSolucion)
            .map(solucionMapper::toDto);
    }

    @Override
    public Mono<SolucionDto> partialUpdate(SolucionDto solucionDto) {
        log.debug("Request to partially update Solucion : {}", solucionDto);

        return solucionRepository
            .findById(solucionDto.getId())
            .map(existingSolucion -> {
                solucionMapper.partialUpdate(existingSolucion, solucionDto);

                return existingSolucion;
            })
            .flatMap(solucionRepository::save)
            .map(solucionMapper::toDto);
    }

    @Override
    public Flux<SolucionDto> findAll(Pageable pageable) {
        log.debug("Request to get all Solucions");
        return solucionRepository.findAllBy(pageable).map(solucionMapper::toDto);
    }

    @Override
    public Flux<SolucionDto> findAll(Filtro filtro) {
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
    public Flux<SolucionDto> findAll(Filtro filtro, Pageable pageable) {
        log.debug("Request to get all SolucionDto");
        return findAllEntities(filtro, pageable).map(solucionMapper::toDto);
    }

    @Override
    public Flux<SolucionDto> findAllByEstado(String estado, Pageable pageable) {
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
    public Mono<SolucionDto> findOne(String id) {
        log.debug("Request to get Solucion : {}", id);
        return solucionRepository.findById(id).map(solucionMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Solucion : {}", id);
        return solucionRepository.deleteById(id);
    }

    @Override
    public Mono<SolucionDto> findOneByLastVersion(String id) {
        log.debug("Request to get Solucion : {}", id);
        return solucionRepository
            .findById(id)
            .map(HistoricoFactory::toHistorico)
            .flatMap(historico -> historicoRepository.findById(historico.getId()).switchIfEmpty(Mono.just(historico)))
            .map(HistoricoSolucion::getSolucion)
            .map(solucionMapper::toDto);
    }

    @Override
    public Mono<DefinicionEvaluacionDto> findOneDefinicionEvaluacion(String solucionId) {
        return definicionEvaluacionRepository.findById(solucionId).map(definicionEvaluacionMapper::toDto);
    }

    @Override
    public Mono<DefinicionEvaluacionDto> saveDefinicionEvaluacion(DefinicionEvaluacionDto definicionDto) {
        return definicionEvaluacionRepository
            .save(definicionEvaluacionMapper.toEntity(definicionDto))
            .map(definicionEvaluacionMapper::toDto);
    }
}
