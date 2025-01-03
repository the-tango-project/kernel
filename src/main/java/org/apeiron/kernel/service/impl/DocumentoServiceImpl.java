package org.apeiron.kernel.service.impl;

import org.apeiron.kernel.domain.Documento;
import org.apeiron.kernel.repository.DocumentoRepository;
import org.apeiron.kernel.service.DocumentoService;
import org.apeiron.kernel.service.dto.DocumentoDto;
import org.apeiron.kernel.service.mapper.DocumentoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Documento}.
 */
@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final Logger log = LoggerFactory.getLogger(DocumentoServiceImpl.class);

    private final DocumentoRepository documentoRepository;

    private final DocumentoMapper documentoMapper;

    public DocumentoServiceImpl(DocumentoRepository documentoRepository, DocumentoMapper documentoMapper) {
        this.documentoRepository = documentoRepository;
        this.documentoMapper = documentoMapper;
    }

    @Override
    public Mono<DocumentoDto> save(DocumentoDto documentoDto) {
        log.debug("Request to save Documento : {}", documentoDto);
        return documentoRepository.save(documentoMapper.toEntity(documentoDto)).map(documentoMapper::toDto);
    }

    @Override
    public Mono<DocumentoDto> update(DocumentoDto documentoDto) {
        log.debug("Request to update Documento : {}", documentoDto);
        return documentoRepository.save(documentoMapper.toEntity(documentoDto)).map(documentoMapper::toDto);
    }

    @Override
    public Mono<DocumentoDto> partialUpdate(DocumentoDto documentoDto) {
        log.debug("Request to partially update Documento : {}", documentoDto);

        return documentoRepository
            .findById(documentoDto.getId())
            .map(existingDocumento -> {
                documentoMapper.partialUpdate(existingDocumento, documentoDto);

                return existingDocumento;
            })
            .flatMap(documentoRepository::save)
            .map(documentoMapper::toDto);
    }

    @Override
    public Flux<DocumentoDto> findAll(Pageable pageable) {
        log.debug("Request to get all Documentos");
        return documentoRepository.findAllBy(pageable).map(documentoMapper::toDto);
    }

    public Mono<Long> countAll() {
        return documentoRepository.count();
    }

    @Override
    public Mono<DocumentoDto> findOne(String id) {
        log.debug("Request to get Documento : {}", id);
        return documentoRepository.findById(id).map(documentoMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Documento : {}", id);
        return documentoRepository.deleteById(id);
    }
}
