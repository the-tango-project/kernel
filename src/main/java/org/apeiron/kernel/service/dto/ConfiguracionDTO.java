package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Configuracion} entity.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ConfiguracionDTO implements Serializable {

    private String carpeta;

    private Boolean hasMoreDocuments;

    private Integer maxNumberOfDocuments;

    private Integer maxFileSize;

    private List<String> mediaTypes = new ArrayList<>();

    private List<DocumentoConfiguracionDTO> documentos = new ArrayList<>();

    private List<RuleDTO> reglas = new ArrayList<>();

    /**
     * Roles que no están autorizados
     */
    private List<RolAutoridad> roles = new ArrayList<>();
}
