package org.apeiron.kernel.service;

import java.util.List;
import org.apeiron.kernel.service.dto.BulkResponseDTO;
import org.apeiron.kernel.service.dto.SolicitudDTO;
import org.apeiron.kernel.service.dto.TransicionContextDTO;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing
 * {@link org.apeiron.kernel.domain.Solicitud}.
 */
public interface ProcesoService {
    /**
     * Realiza la transición de estado de una solicitud aplicando las reglas,
     * posaction y notificaciones definididas en la solución
     *
     * @param transicion the transition context
     * @return Mono<SolicitudDTO>
     */
    Mono<SolicitudDTO> doTransicion(TransicionContextDTO transicion);

    /**
     * Realiza la transición de estado de una o más solicitudes aplicando las
     * reglas, posaction y notificaciones definididas en la solución.
     *
     * Este proceso se ejecuta en segundo plano dado que es una operación para
     * varios registros
     *
     * @param transicion the transition context
     * @return Mono<SolicitudDTO>
     */
    Mono<BulkResponseDTO> doMultipleTransicions(List<TransicionContextDTO> transicions);

    /**
     * Realiza la creación de una o más solicitudes aplicando las
     * reglas, posaction y notificaciones definididas en la solución.
     *
     * Este proceso se ejecuta en segundo plano dado que es una operación para
     * varios registros
     *
     * @param solicitudes las solicitudes
     * @return Mono<SolicitudDTO>
     */
    Mono<BulkResponseDTO> doMultipleCreations(List<SolicitudDTO> solicitudes);

    /**
     * Realiza el guardado parcial de la solicitud, acotado a la información del
     * formulario que se desea guardar. Este método ejecuta las validaciones
     * definidas para el formulario, de tal manera que sea seguro guardar la
     * información de manera parcial y cuidando la consistencia de los datos
     *
     * @param solicitud la solicitud
     * @param formId    id del formulario
     * @return Mono<SolicitudDTO>
     */
    Mono<SolicitudDTO> saveForm(SolicitudDTO solicitud, String formId);
}
