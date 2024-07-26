package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Es la respuesta que hace un evaluador cuando lleva a cabo una revisión. Aquí
 * se guarda el resultado del cuestionario
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Respuesta implements AbstractExtensible, Serializable {

    private static final long serialVersionUID = 1L;

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
