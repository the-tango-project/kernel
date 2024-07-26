package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Resultados de una evaluación
 * FIXME: Refactorizar esta clase para que no se dependa de ella
 *
 * @version 1.0
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "resultados")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Resultado extends AbstractAuditingEntity<String> implements AbstractExtensible {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("convocatoria")
    private String convocatoria;

    @Field("motivo")
    private String motivo;

    @Field("nivel")
    private String nivel;

    @Field("solicitudUrl")
    private String solicitudUrl;

    @Field("perfilUrl")
    private String perfilUrl;

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
