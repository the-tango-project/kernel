package org.apeiron.kernel.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.TipoPaginado;
import org.apeiron.kernel.domain.enumeration.TipoVista;

/**
 * FIXME: Cambiar esta clase de tal manera que su propósito sea más entendible,
 * ya que en realidad no representa a una vista que resume algo. Sugerencias son
 * ConfiguracionBandejaDeEntrada, BandejaDeEntrada.
 * 
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VistaResumen {

    private TipoVista tipoVista;

    private TipoPaginado tipoPaginado;

    private String icon;

    private String titulo;

    private String descripcion;

    private String pieDePagina;

    private List<MascaraEstado> mascaraEstados = new ArrayList<>();

    private List<Button> buttons = new ArrayList<>();

    private List<Columna> columnas = new ArrayList<>();

    private List<String> insignias = new ArrayList<>();
}
