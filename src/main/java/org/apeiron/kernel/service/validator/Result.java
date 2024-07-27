package org.apeiron.kernel.service.validator;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.dto.RuleDto;

@Data
@Builder
public class Result {

    private ContextDto contexto;
    private List<RuleDto> errores;
}
