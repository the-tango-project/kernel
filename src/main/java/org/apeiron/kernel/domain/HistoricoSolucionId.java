package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Solucion.
 */
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Document(collection = "soluciones")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistoricoSolucionId implements Serializable {

    private String id;
    private int version;
}
