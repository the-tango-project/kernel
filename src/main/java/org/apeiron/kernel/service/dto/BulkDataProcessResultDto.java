package org.apeiron.kernel.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;

/**
 * A Dto for the {@link org.apeiron.kernel.domain.Solicitud} entity.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BulkDataProcessResultDto implements Serializable {

    private boolean success;

    private String solicitudId;

    //FIXME: Eliminar
    private String cvu;

    private EstadoSolicitud destino;

    private List<RuleDto> errors;
}
