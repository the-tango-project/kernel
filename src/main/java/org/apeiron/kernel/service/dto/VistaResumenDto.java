package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.TipoPaginado;
import org.apeiron.kernel.domain.enumeration.TipoVista;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VistaResumenDto implements Serializable {

    private TipoVista tipoVista;

    private TipoPaginado tipoPaginado;

    private String icon;

    private String titulo;

    private String descripcion;

    private String pieDePagina;

    private List<MascaraEstadoDto> mascaraEstados = new ArrayList<>();

    private List<ButtonDto> buttons = new ArrayList<>();

    private List<ColumnaDto> columnas = new ArrayList<>();

    private List<String> insignias = new ArrayList<>();
}
