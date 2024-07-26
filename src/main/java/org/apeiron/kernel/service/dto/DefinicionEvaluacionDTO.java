package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO for the {@link org.apeiron.kernel.domain.Respuesta} entity.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefinicionEvaluacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private List<DefinicionRevisionDTO> revisiones = new ArrayList<>();
}
