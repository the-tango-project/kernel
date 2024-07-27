package org.apeiron.kernel.service.impl;

import static org.apeiron.kernel.service.util.QueryHelper.buildBitacoraQuery;

import org.apeiron.kernel.domain.bitacora.Bitacora;
import org.apeiron.kernel.repository.BitacoraRepository;
import org.apeiron.kernel.service.BitacoraService;
import org.apeiron.kernel.service.dto.BitacoraDto;
import org.apeiron.kernel.service.mapper.BitacoraMapper;
import org.apeiron.kernel.service.util.Filtro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Bitacora}.
 */
@Service
public class BitacoraServiceImpl implements BitacoraService {

    private final Logger log = LoggerFactory.getLogger(BitacoraServiceImpl.class);

    private final BitacoraRepository bitacoraRepository;

    private final BitacoraMapper bitacoraMapper;

    public BitacoraServiceImpl(BitacoraRepository bitacoraRepository, BitacoraMapper bitacoraMapper) {
        this.bitacoraRepository = bitacoraRepository;
        this.bitacoraMapper = bitacoraMapper;
    }

    @Override
    public Mono<BitacoraDto> save(BitacoraDto bitacoraDto) {
        log.debug("Request to save Bitacora : {}", bitacoraDto);
        return bitacoraRepository.save(bitacoraMapper.toEntity(bitacoraDto)).map(bitacoraMapper::toDto);
    }

    @Override
    public Flux<BitacoraDto> findAll(Pageable pageable) {
        log.debug("Request to get all Bitacoras");
        return bitacoraRepository.findAllBy(pageable).map(bitacoraMapper::toDto);
    }

    public Mono<Long> countAll() {
        return bitacoraRepository.count();
    }

    @Override
    public Mono<BitacoraDto> findOne(String id) {
        log.debug("Request to get Bitacora : {}", id);
        return bitacoraRepository.findById(id).map(bitacoraMapper::toDto);
    }

    @Override
    public Disposable saveAsynchronous(BitacoraDto bitacora) {
        log.debug("Request to save Bitacora : {}", bitacora);
        return bitacoraRepository.save(bitacoraMapper.toEntity(bitacora)).subscribe();
    }

    @Override
    public Flux<BitacoraDto> findAll(Filtro filtro) {
        log.debug("Request to get all Bitacoras by filtro");
        return bitacoraRepository.findAll(buildBitacoraQuery(filtro)).map(bitacoraMapper::toDto);
    }

    @Override
    public Flux<BitacoraDto> findAll(Filtro filtro, Sort sort) {
        log.debug("Request to get all Bitacoras by filtro");
        return bitacoraRepository.findAll(buildBitacoraQuery(filtro), sort).map(bitacoraMapper::toDto);
    }
}
