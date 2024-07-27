package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A Dto for the {@link org.apeiron.kernel.domain.SolucionConfiguracion}
 * entity.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolucionConfiguracionDto implements Serializable {

    private Integer maxNumberOfSolicitudesPerUser;
}
