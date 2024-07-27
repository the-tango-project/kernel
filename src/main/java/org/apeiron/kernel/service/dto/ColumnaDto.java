package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.apeiron.kernel.domain.enumeration.TipoReglaFiltro;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ColumnaDto implements Serializable {

    private String id;
    private String nombre;
    private String path;
    private boolean filter;
    private String expresion;
    private TipoReglaFiltro tipoReglaFiltro;
    private List<String> filtroValores = new ArrayList<>();
    private List<RolAutoridad> roles = new ArrayList<>();
}
