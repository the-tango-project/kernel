package org.apeiron.kernel.service.impl;

import org.apeiron.kernel.domain.Form;
import org.apeiron.kernel.repository.FormRepository;
import org.apeiron.kernel.service.FormService;
import org.apeiron.kernel.service.dto.FormDTO;
import org.apeiron.kernel.service.mapper.FormMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Form}.
 */
@Service
public class FormServiceImpl implements FormService {

    private final Logger log = LoggerFactory.getLogger(FormServiceImpl.class);

    private final FormRepository formRepository;

    private final FormMapper formMapper;

    public FormServiceImpl(FormRepository formRepository, FormMapper formMapper) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
    }

    @Override
    public Mono<FormDTO> save(FormDTO formDTO) {
        log.debug("Request to save Form : {}", formDTO);
        return formRepository.save(formMapper.toEntity(formDTO)).map(formMapper::toDto);
    }

    @Override
    public Mono<FormDTO> update(FormDTO formDTO) {
        log.debug("Request to update Form : {}", formDTO);
        return formRepository.save(formMapper.toEntity(formDTO)).map(formMapper::toDto);
    }

    @Override
    public Mono<FormDTO> partialUpdate(FormDTO formDTO) {
        log.debug("Request to partially update Form : {}", formDTO);

        return formRepository
            .findById(formDTO.getId())
            .map(existingForm -> {
                formMapper.partialUpdate(existingForm, formDTO);

                return existingForm;
            })
            .flatMap(formRepository::save)
            .map(formMapper::toDto);
    }

    @Override
    public Flux<FormDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Forms");
        return formRepository.findAllBy(pageable).map(formMapper::toDto);
    }

    public Mono<Long> countAll() {
        return formRepository.count();
    }

    @Override
    public Mono<FormDTO> findOne(String id) {
        log.debug("Request to get Form : {}", id);
        return formRepository.findById(id).map(formMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Form : {}", id);
        return formRepository.deleteById(id);
    }
}
