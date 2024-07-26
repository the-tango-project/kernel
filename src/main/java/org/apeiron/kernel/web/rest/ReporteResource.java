package org.apeiron.kernel.web.rest;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.service.ReporteService;
import org.apeiron.kernel.service.dto.ReporteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing {@link org.apeiron.kernel.domain.Form}.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReporteResource {

    private final Logger log = LoggerFactory.getLogger(ReporteResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReporteService reporteService;

    /**
     * {@code GET  /reportes/:id} : get the "id" reportes.
     *
     * @param id the id of solution id.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the reporteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reportes/soluciones/{id}/solicitudes")
    public Mono<ResponseEntity<List<ReporteDTO>>> countTotalSolicitudesByEstado(@PathVariable String id) {
        log.debug("REST request to get Reporte : {}", id);
        return reporteService.countTotalSolicitudesByEstado(id).collectList().map(reporte -> ResponseEntity.ok().body(reporte));
    }

    private Mono<Void> downloadFile(ServerHttpResponse response, File file, String fileName) {
        ZeroCopyHttpOutputMessage zeroCopyHttpOutputMessage = (ZeroCopyHttpOutputMessage) response;
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=".concat(URLEncoder.encode(fileName, UTF_8)));
        return zeroCopyHttpOutputMessage.writeWith(file, 0, file.length());
    }
}
