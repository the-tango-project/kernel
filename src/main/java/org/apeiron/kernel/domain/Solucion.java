package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
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
import org.apeiron.kernel.domain.proceso.Proceso;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Solucion.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Document(collection = "soluciones")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Solucion extends AbstractAuditingEntity<String> implements AbstractExtensible {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("titulo")
    private String titulo;

    @Field("descripcion")
    private String descripcion;

    @Field("estado")
    private EstadoSolucion estado;

    @Field("tipo")
    private TipoSolucion tipo;

    @Field("tipo_acceso")
    private TipoAcceso tipoAcceso;

    @Field("tipo_menu")
    private TipoMenu tipoMenu;

    @Field("componentes")
    private List<Componente> componentes = new ArrayList<>();

    @Field("tags")
    private List<String> tags = new ArrayList<>();

    @Field("mensaje")
    private Mensaje mensaje = new Mensaje();

    // FIXME: Dejar este componente dinámico
    @Field("calendario")
    private Calendario calendario = new Calendario();

    @Field("proceso")
    private Proceso proceso = new Proceso();

    @Field("reglas")
    private List<Rule> reglas = new ArrayList<>();

    @Field("autoridades")
    private List<Autoridad> autoridades = new ArrayList<>();

    @Field("vistaResumen")
    private VistaResumen vistaResumen = new VistaResumen();

    @Field("version")
    private int version;

    @Field("isVisible")
    private Boolean visible;

    // FIXME: Pasar estra propiedad al objeto SoluciónConfiguracion.
    @Field("mail_template")
    private String mailTemplate;

    @Field("configuracion")
    private SolucionConfiguracion configuracion = new SolucionConfiguracion();

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
