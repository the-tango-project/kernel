package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Revisor.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Revisor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("revisor_id")
    private String revisorId;

    @Field("nombre")
    private String nombre;
}
