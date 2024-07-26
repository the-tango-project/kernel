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
 * Información general de una persona
 *
 * @version 1.0
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reportes")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Reporte extends AbstractAuditingEntity<String> implements AbstractExtensible {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private int count;

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
