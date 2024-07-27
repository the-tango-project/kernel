package org.apeiron.kernel.service;

import static org.apeiron.kernel.service.util.QueryHelper.buildQuery;

import org.apeiron.kernel.domain.Comentario;
import org.apeiron.kernel.repository.ComentarioRepository;
import org.apeiron.kernel.service.dto.ComentarioDto;
import org.apeiron.kernel.service.mapper.ComentarioMapper;
import org.apeiron.kernel.service.util.Filtro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Comentario}.
 */
@Service
public class ComentarioService {

    public static int count = 0;

    private final Logger log = LoggerFactory.getLogger(ComentarioService.class);

    private final ComentarioRepository comentarioRepository;

    private final ComentarioMapper comentarioMapper;

    public ComentarioService(ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    /**
     * Save a comentario.
     *
     * @param comentarioDto the entity to save.
     * @return the persisted entity.
     */
    public Mono<ComentarioDto> save(ComentarioDto comentarioDto) {
        log.debug("Request to save Comentario : {}", comentarioDto);
        return comentarioRepository.save(comentarioMapper.toEntity(comentarioDto)).map(comentarioMapper::toDto);
    }

    /**
     * Save a comentario.
     *
     * @param comentarioDto the entity to save.
     * @return the persisted entity.
     */
    public Disposable saveAsynchronous(ComentarioDto comentario) {
        log.debug("Request to save Comentario : {}", comentario);
        return comentarioRepository.save(comentarioMapper.toEntity(comentario)).subscribe();
    }

    /**
     * Update a comentario.
     *
     * @param comentarioDto the entity to save.
     * @return the persisted entity.
     */
    public Mono<ComentarioDto> update(ComentarioDto comentarioDto) {
        log.debug("Request to update Comentario : {}", comentarioDto);
        return comentarioRepository.save(comentarioMapper.toEntity(comentarioDto)).map(comentarioMapper::toDto);
    }

    /**
     * Partially update a comentario.
     *
     * @param comentarioDto the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ComentarioDto> partialUpdate(ComentarioDto comentarioDto) {
        log.debug("Request to partially update Comentario : {}", comentarioDto);

        return comentarioRepository
            .findById(comentarioDto.getId())
            .map(existingComentario -> {
                comentarioMapper.partialUpdate(existingComentario, comentarioDto);

                return existingComentario;
            })
            .flatMap(comentarioRepository::save)
            .map(comentarioMapper::toDto);
    }

    /**
     * Get all the comentarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<ComentarioDto> findAll(Filtro filtro, Pageable pageable) {
        log.debug("Request to get all Comentarios");
        return comentarioRepository
            .findAll(buildQuery(filtro), pageable.getSort())
            .skip(pageable.getOffset())
            .take(pageable.getPageSize())
            .map(comentarioMapper::toDto);
    }

    /**
     * Returns the number of comentarios available.
     *
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll(Filtro filtro) {
        return comentarioRepository.count(buildQuery(filtro));
    }

    /**
     * Get one comentario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Mono<ComentarioDto> findOne(String id) {
        log.debug("Request to get Comentario : {}", id);
        return comentarioRepository.findById(id).map(comentarioMapper::toDto);
    }
}
