package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoConfiguracionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;

    private String nombrePropiedad;

    private boolean requerido;

    private Integer max;

    private List<String> mediaTypes = new ArrayList<>();

    private String expresion;
}
