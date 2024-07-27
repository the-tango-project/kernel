package org.apeiron.kernel.service.impl;

import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.repository.ResultadoRepository;
import org.apeiron.kernel.service.ResultadoService;
import org.apeiron.kernel.service.dto.ResultadoDto;
import org.apeiron.kernel.service.mapper.ResultadoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ResultadoServiceImpl implements ResultadoService {

    private final Logger log = LoggerFactory.getLogger(ResultadoServiceImpl.class);

    private final ResultadoRepository resultadoRepository;

    private final ResultadoMapper resultadoMapper;

    @Override
    public Mono<ResultadoDto> save(ResultadoDto resultadoDto) {
        log.debug("Request to save Resultado : {}", resultadoDto);
        return resultadoRepository.save(resultadoMapper.toEntity(resultadoDto)).map(resultadoMapper::toDto);
    }

    @Override
    public Mono<ResultadoDto> findOne(String cvu) {
        log.debug("Request to get resultado by cvu : {}", cvu);
        return resultadoRepository.findById(cvu).map(resultadoMapper::toDto);
    }
}
