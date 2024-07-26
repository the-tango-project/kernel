package org.apeiron.kernel.service.impl;

import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.repository.SolicitudRepository;
import org.apeiron.kernel.service.ReporteService;
import org.apeiron.kernel.service.dto.ReporteDTO;
import org.apeiron.kernel.service.mapper.ReporteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final Logger log = LoggerFactory.getLogger(ReporteServiceImpl.class);

    private final SolicitudRepository solicitudRepository;

    private final ReporteMapper reporteMapper;

    @Override
    public Flux<ReporteDTO> countTotalSolicitudesByEstado(String solucionId) {
        log.debug("Request to get Reporte by solucionId : {}", solucionId);
        return solicitudRepository.countTotalSolicitudesByEstado(solucionId).map(reporteMapper::toDto);
    }
}
