package org.apeiron.kernel.service.validator;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.apeiron.kernel.service.dto.ContextDTO;
import org.apeiron.kernel.service.dto.RuleDTO;

@Data
@Builder
public class Result {

    private ContextDTO contexto;
    private List<RuleDTO> errores;
}
