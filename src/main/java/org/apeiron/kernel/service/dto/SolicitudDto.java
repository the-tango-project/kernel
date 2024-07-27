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
 * A Dto for the {@link org.apeiron.kernel.domain.Solicitud} entity.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitudDto extends AbstractExtensibleDto {

    @EqualsAndHashCode.Include
    private String id;

    private String nombre;

    private String usuario;

    private String solucionId;

    private EstadoSolicitud estado;

    private PersonaDto solicitante = new PersonaDto();

    private List<RevisorResumenDto> revisores = new ArrayList<>();

    public SolicitudDto usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }
}
