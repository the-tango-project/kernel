package org.apeiron.kernel.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.repository.SolicitudRepository;
import org.apeiron.kernel.service.ProcesoService;
import org.apeiron.kernel.service.SolicitudService;
import org.apeiron.kernel.service.dto.BulkResponseDTO;
import org.apeiron.kernel.service.dto.SolicitudDTO;
import org.apeiron.kernel.service.dto.TransicionContextDTO;
import org.apeiron.kernel.service.exception.NotFoundException;
import org.apeiron.kernel.service.exception.RulesException;
import org.apeiron.kernel.service.util.Filtro;
import org.apeiron.kernel.service.util.FiltroHelper;
import org.apeiron.kernel.web.rest.errors.BadRequestAlertException;
import org.apeiron.kernel.web.rest.errors.InvalidSolicitudException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing
 * {@link org.apeiron.kernel.domain.Solicitud}.
 */
@RestController
@RequestMapping("/api")
public class SolicitudResource {

    private final Logger log = LoggerFactory.getLogger(SolicitudResource.class);

    @Autowired
    private ProcesoService procesoService;

    private static final String ENTITY_NAME = "solicitud";

    @Value("${kernel.clientApp.name}")
    private String applicationName;

    private final SolicitudService solicitudService;

    private final SolicitudRepository solicitudRepository;

    public SolicitudResource(
            SolicitudService solicitudService,
            SolicitudRepository solicitudRepository) {
        this.solicitudService = solicitudService;
        this.solicitudRepository = solicitudRepository;
    }

    /**
     * {@code POST  /solicituds} : Create a new solicitud.
     *
     * @param solicitudDTO the solicitudDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new solicitudDTO, or with status {@code 400 (Bad Request)}
     *         if the solicitud has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/solicitudes")
    public Mono<ResponseEntity<SolicitudDTO>> createSolicitud(@RequestBody SolicitudDTO solicitudDTO)
            throws URISyntaxException {
        log.debug("REST request to save Solicitud : {}", solicitudDTO);
        if (solicitudDTO.getId() != null) {
            throw new BadRequestAlertException("A new solicitud cannot already have an ID", ENTITY_NAME, "idexists");
        }
        solicitudDTO.setEstado(EstadoSolicitud.EN_CAPTURA);
        return solicitudService
                .save(solicitudDTO)
                .map(result -> {
                    try {
                        return ResponseEntity
                                .created(new URI("/api/solicitudes/" + result.getId()))
                                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                                        result.getId()))
                                .body(result);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @PostMapping("/solicitudes/migracion")
    public Mono<ResponseEntity<SolicitudDTO>> migrarSolicitudes(@RequestBody SolicitudDTO solicitudDTO)
            throws URISyntaxException {
        log.debug("REST request to save Solicitud : {}", solicitudDTO);
        return solicitudService
                .migrar(solicitudDTO)
                .map(result -> {
                    try {
                        return ResponseEntity
                                .created(new URI("/api/solicitudes/" + result.getId()))
                                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                                        result.getId()))
                                .body(result);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * {@code PUT  /solicitudes/:id} : Updates an existing solicitud.
     *
     * @param id           the id of the solicitudDTO to save.
     * @param solicitudDTO the solicitudDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated solicitudDTO,
     *         or with status {@code 400 (Bad Request)} if the solicitudDTO is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         solicitudDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/solicitudes/{id}")
    public Mono<ResponseEntity<SolicitudDTO>> updateSolicitud(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody SolicitudDTO solicitudDTO) throws URISyntaxException {
        log.debug("REST request to update Solicitud : {}, {}", id, solicitudDTO);
        if (solicitudDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solicitudDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solicitudRepository
                .existsById(id)
                .flatMap(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                    }

                    return solicitudService
                            .update(solicitudDTO)
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                            .map(result -> ResponseEntity
                                    .ok()
                                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                            result.getId()))
                                    .body(result));
                });
    }

    /**
     * {@code PUT  /solicitudes/aprobacionMasivaAcreditacion/} : Updates an existing
     * solicitud and send to formalizacion.
     *
     * @param Filtro.idsSolicitud array id solicitud .
     *
     * @return the Mono<ResponseEntity<Void>> with status {@code 200 (OK)}
     *         or with status {@code 404 (Not found)} if the
     *         solicitudDTO can't find.
     */
    @PutMapping("/solicitudes/aprobacionMasivaAcreditacion/")
    public Mono<ResponseEntity<Object>> aprobacionMasivaAcreditacion(
            @RequestBody List<TransicionContextDTO> transiciones) {
        if (transiciones.isEmpty()) {
            return Mono.error(new NotFoundException("No se proporcionaron transiciones"));
        }

        List<Mono<SolicitudDTO>> resultados = new ArrayList<>();

        for (TransicionContextDTO transicionContextDto : transiciones) {
            resultados.add(procesoService.doTransicion(transicionContextDto));
        }

        return Flux
                .merge(resultados)
                .then(Mono.just(ResponseEntity.ok().build()))
                .onErrorMap(RulesException.class,
                        e -> new InvalidSolicitudException("Invalid id", "invalidSolicitud", e.getErrores()));
    }

    /**
     * {@code PATCH  /solicituds/:id} : Partial updates given fields of an existing
     * solicitud, field will ignore if it is null
     *
     * @param id           the id of the solicitudDTO to save.
     * @param solicitudDTO the solicitudDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated solicitudDTO,
     *         or with status {@code 400 (Bad Request)} if the solicitudDTO is not
     *         valid,
     *         or with status {@code 404 (Not Found)} if the solicitudDTO is not
     *         found,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         solicitudDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/solicitudes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SolicitudDTO>> partialUpdateSolicitud(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody SolicitudDTO solicitudDTO) throws URISyntaxException {
        log.debug("REST request to partial update Solicitud partially : {}, {}", id, solicitudDTO);
        if (solicitudDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solicitudDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solicitudRepository
                .existsById(id)
                .flatMap(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                    }

                    Mono<SolicitudDTO> result = solicitudService.partialUpdate(solicitudDTO);

                    return result
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                            .map(res -> ResponseEntity
                                    .ok()
                                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                            res.getId()))
                                    .body(res));
                });
    }

    /**
     * {@code GET  /solicitudes} : get all the solicituds.
     *
     * @param pageable the pagination information.
     * @param request  a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of solicituds in body.
     */
    @GetMapping("/solicitudes")
    public Mono<ResponseEntity<List<SolicitudDTO>>> getAllSolicituds(
            Filtro filtro,
            @RequestParam Map<String, String> requestParams,
            @org.springdoc.core.annotations.ParameterObject Pageable pageable,
            ServerHttpRequest request) {
        filtro = FiltroHelper.mapFiltersFromRequestParams(filtro, requestParams);
        return solicitudService
                .countAll(filtro)
                .zipWith(solicitudService.findAll(filtro, pageable).collectList())
                .map(countWithEntities -> ResponseEntity
                        .ok()
                        .headers(
                                PaginationUtil.generatePaginationHttpHeaders(
                                        UriComponentsBuilder.fromHttpRequest(request),
                                        new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())))
                        .body(countWithEntities.getT2()));
    }

    @GetMapping("/solicitudes/invitaciones")
    public Mono<ResponseEntity<List<SolicitudDTO>>> getAllSolicitudesByRevisor(
            @RequestParam(required = false) String solucionId,
            @org.springdoc.core.annotations.ParameterObject Pageable pageable,
            ServerHttpRequest request) {
        log.debug("REST request to get a page of Solicituds");
        return solicitudService
                .countAll()
                .zipWith(solicitudService.findAllInvitaciones().collectList())
                .map(countWithEntities -> ResponseEntity
                        .ok()
                        .headers(
                                PaginationUtil.generatePaginationHttpHeaders(
                                        UriComponentsBuilder.fromHttpRequest(request),
                                        new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())))
                        .body(countWithEntities.getT2()));
    }

    /**
     * {@code GET  /solicituds/:id} : get the "id" solicitud.
     *
     * @param id the id of the solicitudDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the solicitudDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/solicitudes/{id}")
    public Mono<ResponseEntity<SolicitudDTO>> getSolicitud(@PathVariable String id) {
        log.debug("REST request to get Solicitud : {}", id);
        Mono<SolicitudDTO> solicitudDTO = solicitudService.findOne(id);
        return ResponseUtil.wrapOrNotFound(solicitudDTO);
    }

    @GetMapping("/solicitudes/solucion/{id}")
    public Mono<ResponseEntity<SolicitudDTO>> getSolicitudBySolucionId(@PathVariable String id) {
        log.debug("REST request to get Solicitud : {}", id);
        Mono<SolicitudDTO> solicitudDTO = solicitudService.findBySolucionId(id);
        return ResponseUtil.wrapOrNotFound(solicitudDTO);
    }

    @PutMapping("/solicitudes/enviar/{id}")
    public Mono<ResponseEntity<SolicitudDTO>> sendSolicitud(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody SolicitudDTO solicitudDTO) throws URISyntaxException {
        log.debug("REST request to update Solicitud : {}, {}", id, solicitudDTO);
        solicitudDTO.setEstado(EstadoSolicitud.ENVIADA);
        return solicitudRepository
                .existsById(id)
                .flatMap(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                    }
                    return solicitudService
                            .update(solicitudDTO)
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                            .map(result -> ResponseEntity
                                    .ok()
                                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                            result.getId()))
                                    .body(result));
                });
    }

    @GetMapping("/solicitudes/{id}/revisores")
    public Mono<ResponseEntity<SolicitudDTO>> getRevisores(@PathVariable String id) {
        log.debug("REST request to get Solicitud : {}", id);
        Mono<SolicitudDTO> solicitudDTO = solicitudService.findRevisoresById(id);
        return ResponseUtil.wrapOrNotFound(solicitudDTO);
    }

    @PutMapping("/solicitudes/{id}/revisores")
    public Mono<ResponseEntity<SolicitudDTO>> updateRevisores(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody SolicitudDTO solicitudDTO) throws URISyntaxException {
        log.debug("REST request to update Solicitud : {}, {}", id, solicitudDTO);
        if (solicitudDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solicitudDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return solicitudRepository
                .existsById(id)
                .flatMap(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                    }

                    return solicitudService
                            .updateRevisores(solicitudDTO)
                            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                            .map(result -> ResponseEntity
                                    .ok()
                                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                            result.getId()))
                                    .body(result));
                });
    }

    @PutMapping("/solicitudes/transition")
    public Mono<ResponseEntity<SolicitudDTO>> enviarSolicitud(@RequestBody TransicionContextDTO transicion)
            throws URISyntaxException {
        return procesoService
                .doTransicion(transicion)
                .onErrorMap(RulesException.class,
                        e -> new InvalidSolicitudException("Invalid id", "invalidSolicitud", e.getErrores()))
                .map(result -> ResponseEntity
                        .ok()
                        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result));
    }

    @PutMapping("/solicitudes/transition/bulk")
    public Mono<ResponseEntity<BulkResponseDTO>> doMultipleTransitions(
            @RequestBody List<TransicionContextDTO> transicions)
            throws URISyntaxException {
        return procesoService
                .doMultipleTransicions(transicions)
                .map(result -> ResponseEntity
                        .ok()
                        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                result.getProcessed() + ""))
                        .body(result));
    }

    @PostMapping("/solicitudes/create/bulk")
    public Mono<ResponseEntity<BulkResponseDTO>> doMultipleCreations(@RequestBody List<SolicitudDTO> solicitudes)
            throws URISyntaxException {
        return procesoService
                .doMultipleCreations(solicitudes)
                .map(result -> ResponseEntity
                        .ok()
                        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                                result.getProcessed() + ""))
                        .body(result));
    }

    @PutMapping("/solicitudes/{id}/{formId}")
    public Mono<ResponseEntity<SolicitudDTO>> updateForm(
            @PathVariable(value = "id", required = false) final String id,
            @PathVariable(value = "formId", required = false) final String formId,
            @RequestBody SolicitudDTO solicitudDTO) throws URISyntaxException {
        log.debug("REST request to update Solicitud : {}, {}", id, solicitudDTO);
        if (solicitudDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, solicitudDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return procesoService
                .saveForm(solicitudDTO, formId)
                .onErrorMap(RulesException.class,
                        e -> new InvalidSolicitudException("Invalid id", "invalidSolicitud", e.getErrores()))
                .map(result -> ResponseEntity
                        .ok()
                        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result));
    }

    /**
     * Realiza búsqueda del listado de solicitudes por solucionId y usuario logueado
     *
     * @param solucionId
     * @param pageable
     * @return
     */
    @GetMapping("/solicitudes/solucionBy/{solucionId}")
    public Mono<ResponseEntity<List<SolicitudDTO>>> findAllByUsuarioAndSolucionId(@PathVariable String solucionId,
            Pageable pageable) {
        return solicitudService.findAllByUsuarioAndSolucionId(solucionId, pageable).collectList()
                .map(ResponseEntity::ok);
    }

    /**
     * Realiza búsqueda del listado de solicitudes por lista de solucionIds y
     * usuario logueado
     *
     * @param filtro
     * @param pageable
     * @return
     */
    @GetMapping("/solicitudes/soluciones")
    public Mono<ResponseEntity<List<SolicitudDTO>>> findAllByAllSoluciones(Filtro filtro, Pageable pageable,
            ServerHttpRequest request) {
        if (filtro.getIdsSolucion().isEmpty() || filtro.getIdsSolucion() == null) {
            throw new BadRequestAlertException("La información es incorrecta", ENTITY_NAME, "invalid");
        }
        return solicitudService.findAllByAllSoluciones(filtro.getIdsSolucion(), pageable).collectList()
                .map(ResponseEntity::ok);
    }
}
