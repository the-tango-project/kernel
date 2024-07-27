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
 * A Dto for the {@link org.apeiron.kernel.domain.Solucion} entity.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolucionDto extends AbstractExtensibleDto {

    @EqualsAndHashCode.Include
    private String id;

    private String titulo;

    private String descripcion;

    private EstadoSolucion estado;

    private TipoSolucion tipo;

    private TipoAcceso tipoAcceso;

    private TipoMenu tipoMenu;

    private MensajeDto mensaje = new MensajeDto();

    private CalendarioDto calendario = new CalendarioDto();

    private ProcesoDto proceso = new ProcesoDto();

    private List<ComponenteDto> componentes = new ArrayList<>();

    private List<String> tags = new ArrayList<>();

    private List<RuleDto> reglas = new ArrayList<>();

    private List<AutoridadDto> autoridades = new ArrayList<>();

    private VistaResumenDto vistaResumen = new VistaResumenDto();

    private int version;

    private String mailTemplate;

    private Boolean visible;

    private SolucionConfiguracionDto configuracion = new SolucionConfiguracionDto();
}
