package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.TipoAviso;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AvisoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;

    private String descripcion;

    private String pieDePagina;

    private TipoAviso tipo;
}
