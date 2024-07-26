package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.TipoMenu;
import org.apeiron.kernel.domain.enumeration.TipoRevision;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Respuesta} entity.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefinicionRevisionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String descripcion;

    private TipoMenu tipoMenu;

    private TipoRevision tipoRevision;

    private boolean verOtrasRevisiones;

    private List<String> revisionesSoloLectura = new ArrayList<>();

    private List<ComponenteDTO> componentes = new ArrayList<>();
}
