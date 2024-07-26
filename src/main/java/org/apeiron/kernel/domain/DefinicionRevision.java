package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.TipoMenu;
import org.apeiron.kernel.domain.enumeration.TipoRevision;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Componente.
 * FIXME: Refactorizar al igual que {@link DefinicionEvaluacion} para que sea
 * más general y no sólo a revisores.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DefinicionRevision implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("id")
    private String id;

    @Field("descripcion")
    private String descripcion;

    @Field("tipo_menu")
    private TipoMenu tipoMenu;

    @Field("tipo_revision")
    private TipoRevision tipoRevision;

    @Field("ver_otras_revisiones")
    private boolean verOtrasRevisiones;

    @Field("revisiones_solo_lectura")
    private List<String> revisionesSoloLectura = new ArrayList<>();

    @Field("componentes")
    private List<Componente> componentes = new ArrayList<>();
}
