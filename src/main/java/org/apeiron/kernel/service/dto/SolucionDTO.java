package org.apeiron.kernel.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoSolucion;
import org.apeiron.kernel.domain.enumeration.TipoAcceso;
import org.apeiron.kernel.domain.enumeration.TipoMenu;
import org.apeiron.kernel.domain.enumeration.TipoSolucion;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Solucion} entity.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolucionDTO extends AbstractExtensibleDTO {

    @EqualsAndHashCode.Include
    private String id;

    private String titulo;

    private String descripcion;

    private EstadoSolucion estado;

    private TipoSolucion tipo;

    private TipoAcceso tipoAcceso;

    private TipoMenu tipoMenu;

    private MensajeDTO mensaje = new MensajeDTO();

    private CalendarioDTO calendario = new CalendarioDTO();

    private ProcesoDTO proceso = new ProcesoDTO();

    private List<ComponenteDTO> componentes = new ArrayList<>();

    private List<String> tags = new ArrayList<>();

    private List<RuleDTO> reglas = new ArrayList<>();

    private List<AutoridadDTO> autoridades = new ArrayList<>();

    private VistaResumenDTO vistaResumen = new VistaResumenDTO();

    private int version;

    private String mailTemplate;

    private Boolean visible;

    private SolucionConfiguracionDTO configuracion = new SolucionConfiguracionDTO();
}
