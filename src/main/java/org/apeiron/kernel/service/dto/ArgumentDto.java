package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.commons.annotations.ArgumentType;

/**
 * A Dto for the {@link org.apeiron.kernel.domain.Rule} entity.
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ArgumentDto implements Serializable {

    private ArgumentType type;
    private String name;
    private Object value;
}
