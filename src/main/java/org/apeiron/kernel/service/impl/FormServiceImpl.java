package org.apeiron.kernel.service.impl;

import org.apeiron.kernel.domain.Form;
import org.apeiron.kernel.repository.FormRepository;
import org.apeiron.kernel.service.FormService;
import org.apeiron.kernel.service.dto.FormDto;
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
    public Mono<FormDto> save(FormDto formDto) {
        log.debug("Request to save Form : {}", formDto);
        return formRepository.save(formMapper.toEntity(formDto)).map(formMapper::toDto);
    }

    @Override
    public Mono<FormDto> update(FormDto formDto) {
        log.debug("Request to update Form : {}", formDto);
        return formRepository.save(formMapper.toEntity(formDto)).map(formMapper::toDto);
    }

    @Override
    public Mono<FormDto> partialUpdate(FormDto formDto) {
        log.debug("Request to partially update Form : {}", formDto);

        return formRepository
            .findById(formDto.getId())
            .map(existingForm -> {
                formMapper.partialUpdate(existingForm, formDto);

                return existingForm;
            })
            .flatMap(formRepository::save)
            .map(formMapper::toDto);
    }

    @Override
    public Flux<FormDto> findAll(Pageable pageable) {
        log.debug("Request to get all Forms");
        return formRepository.findAllBy(pageable).map(formMapper::toDto);
    }

    public Mono<Long> countAll() {
        return formRepository.count();
    }

    @Override
    public Mono<FormDto> findOne(String id) {
        log.debug("Request to get Form : {}", id);
        return formRepository.findById(id).map(formMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Form : {}", id);
        return formRepository.deleteById(id);
    }
}
