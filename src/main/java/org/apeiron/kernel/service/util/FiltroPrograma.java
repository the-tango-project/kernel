package org.apeiron.kernel.service.util;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroPrograma implements Serializable {

    private String programaId;
    private String clave;
    private String coordinadorCvu;
    private String coordinadorLogin;
}
