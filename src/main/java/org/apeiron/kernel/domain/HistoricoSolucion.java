package org.apeiron.kernel.domain;

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
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Document(collection = "historicoSoluciones")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistoricoSolucion extends AbstractAuditingEntity<HistoricoSolucionId> {

    @Id
    @EqualsAndHashCode.Include
    private HistoricoSolucionId id;

    @Field("solucion")
    private Solucion solucion;
}
