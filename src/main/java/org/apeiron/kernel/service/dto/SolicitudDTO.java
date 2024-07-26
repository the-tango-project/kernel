package org.apeiron.kernel.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Solicitud} entity.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitudDTO extends AbstractExtensibleDTO {

    @EqualsAndHashCode.Include
    private String id;

    private String nombre;

    private String usuario;

    private String solucionId;

    private EstadoSolicitud estado;

    private PersonaDTO solicitante = new PersonaDTO();

    private List<RevisorResumenDTO> revisores = new ArrayList<>();

    public SolicitudDTO usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }
}
