package org.apeiron.kernel.service.impl;

import static org.apeiron.kernel.service.util.QueryHelper.buildRevisionQuery;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.domain.Revision;
import org.apeiron.kernel.domain.Revisor;
import org.apeiron.kernel.domain.RevisorResumen;
import org.apeiron.kernel.repository.RevisionRepository;
import org.apeiron.kernel.repository.SolicitudRepository;
import org.apeiron.kernel.security.SecurityUtils;
import org.apeiron.kernel.service.RevisionService;
import org.apeiron.kernel.service.dto.RevisionDTO;
import org.apeiron.kernel.service.mapper.RevisionMapper;
import org.apeiron.kernel.service.util.FiltroRevision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Revision}.
 */
@Service
@RequiredArgsConstructor
public class RevisionServiceImpl implements RevisionService {

    private final Logger log = LoggerFactory.getLogger(RevisionServiceImpl.class);

    private final RevisionRepository revisionRepository;

    private final RevisionMapper revisionMapper;

    private final SolicitudRepository solicitudRepository;

    @Override
    public Mono<RevisionDTO> save(RevisionDTO revisionDTO) {
        log.debug("Request to save Revision : {}", revisionDTO);
        return revisionRepository.save(revisionMapper.toEntity(revisionDTO)).map(revisionMapper::toDto);
    }

    @Override
    public Mono<RevisionDTO> update(RevisionDTO revisionDTO) {
        log.debug("Request to update Revision : {}", revisionDTO);
        return revisionRepository.save(revisionMapper.toEntity(revisionDTO)).map(revisionMapper::toDto);
    }

    @Override
    public Mono<RevisionDTO> partialUpdate(RevisionDTO revisionDTO) {
        log.debug("Request to partially update Revision : {}", revisionDTO);

        return revisionRepository
            .findById(revisionDTO.getId())
            .map(existingRevision -> {
                revisionMapper.partialUpdate(existingRevision, revisionDTO);

                return existingRevision;
            })
            .flatMap(revisionRepository::save)
            .map(revisionMapper::toDto);
    }

    @Override
    public Flux<RevisionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Revisions");
        return SecurityUtils
            .getCurrentCvu()
            .switchIfEmpty(Mono.justOrEmpty("anonymous"))
            .flatMap(usuario -> revisionRepository.findAllByRevisor(usuario, pageable).map(revisionMapper::toDto).collectList())
            .flatMapIterable(re -> re);
    }

    public Mono<Long> countAll() {
        return revisionRepository.count();
    }

    @Override
    public Mono<RevisionDTO> findOne(String id) {
        log.debug("Request to get Revision : {}", id);
        return revisionRepository.findById(id).map(revisionMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Revision : {}", id);
        return revisionRepository.deleteById(id);
    }

    @Override
    public Mono<RevisionDTO> confirmarRevision(RevisionDTO revisionDTO) {
        return SecurityUtils
            .getCurrentCvu()
            .switchIfEmpty(Mono.just("anonymous"))
            .map(usuario -> mapRevision(revisionMapper.toEntity(revisionDTO), usuario))
            .flatMap(revision ->
                revisionRepository
                    .findBysolicitudIdAndRevisorId(
                        revision.getSolicitudResumen().getSolicitudId(),
                        revision.getRevisor().getRevisorId(),
                        revision.getEvaluacionId()
                    )
                    .switchIfEmpty(Mono.just(revision))
            )
            .zipWith(solicitudRepository.findById(revisionDTO.getSolicitudResumen().getSolicitudId()))
            .flatMap(result -> {
                if (result.getT1().getId() != null) {
                    return Mono.just(result.getT1());
                } else {
                    int indx = resolveIndex(result.getT1().getRevisor().getRevisorId(), result.getT2().getRevisores());
                    result.getT2().getRevisores().get(indx).setEstado(revisionDTO.getEstado());
                    return solicitudRepository.save(result.getT2()).flatMap(solicitud -> revisionRepository.save(result.getT1()));
                }
            })
            .map(revisionMapper::toDto);
    }

    private static int resolveIndex(String usuarioId, List<RevisorResumen> revisores) {
        RevisorResumen currentRevisor = null;
        for (int i = 0; i < revisores.size(); i++) {
            currentRevisor = revisores.get(i);
            if (currentRevisor.getCvu().equals(usuarioId)) {
                return i;
            }
        }
        return -1;
    }

    private static Revision mapRevision(Revision revision, String usuario) {
        Revisor revisor = Revisor.builder().revisorId(usuario).build();
        revision.setRevisor(revisor);
        return revision;
    }

    @Override
    public Mono<RevisionDTO> findBysolicitudIdAndRevisorId(String solicitudId, String revisorId, String evaluacionId) {
        log.debug("Request to get Revision : {}", solicitudId);
        return revisionRepository.findBysolicitudIdAndRevisorId(solicitudId, revisorId, evaluacionId).map(revisionMapper::toDto);
    }

    @Override
    public Flux<RevisionDTO> findAllRevisionesBySolicitudId(String solicitudId, String evaluacionId) {
        log.debug("Request to get Revision : {}", solicitudId);
        return revisionRepository.findAllRevisionesBySolicitudIdAndEvaluacionId(solicitudId, evaluacionId).map(revisionMapper::toDto);
    }

    @Override
    public Mono<RevisionDTO> findByEvaluacionIdAndRevisorId(FiltroRevision filtro) {
        log.debug("Request to get Revision : {}", filtro);
        return revisionRepository
            .findByEvaluacionIdAndRevisorId(filtro.getEvaluacionId(), filtro.getRevisorId())
            .map(revisionMapper::toDto);
    }

    @Override
    public Flux<RevisionDTO> findAll(FiltroRevision filtro, Pageable pageable) {
        log.debug("Request to get all Revisions");

        return revisionRepository
            .findAll(buildRevisionQuery(filtro), pageable.getSort())
            .skip(pageable.getOffset())
            .take(pageable.getPageSize())
            .map(revisionMapper::toDto);
    }

    @Override
    public Mono<Long> countAll(FiltroRevision filtro) {
        return revisionRepository.count(buildRevisionQuery(filtro));
    }
}
