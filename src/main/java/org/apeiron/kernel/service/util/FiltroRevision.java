package org.apeiron.kernel.service.util;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apeiron.kernel.domain.enumeration.EstadoRevision;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroRevision implements Serializable {

    private String evaluacionId;
    private String revisorId;
    private EstadoRevision estado;
    private String solucionId;
    private String comision;
    private String solicitudId;
    private List<String> revisoresId;
}
