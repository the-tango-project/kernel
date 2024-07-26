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
public class VistaResumenDTO implements Serializable {

    private TipoVista tipoVista;

    private TipoPaginado tipoPaginado;

    private String icon;

    private String titulo;

    private String descripcion;

    private String pieDePagina;

    private List<MascaraEstadoDTO> mascaraEstados = new ArrayList<>();

    private List<ButtonDTO> buttons = new ArrayList<>();

    private List<ColumnaDTO> columnas = new ArrayList<>();

    private List<String> insignias = new ArrayList<>();
}
