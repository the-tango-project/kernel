package org.apeiron.kernel.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Solucion.
 * FIXME: Refactorizar de tal manera que una revisión se vuelva un permiso
 * genérico que pueda soportar más roles y no sólo el de un revisor
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Document(collection = "definicionEvaluaciones")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DefinicionEvaluacion extends AbstractAuditingEntity<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("revisiones")
    private List<DefinicionRevision> revisiones = new ArrayList<>();
}
