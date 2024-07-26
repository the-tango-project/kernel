package org.apeiron.kernel.domain;

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
public class Columna {

    /**
     * Id de la columna
     */
    private String id;

    /**
     * Nombre de la columna
     */
    private String nombre;

    /**
     * Ruta en la que se puede encontrar la columna. Esta ruta esta escrita en
     * función del objecto Solicitud
     */
    private String path;

    /**
     * Propiedad que indica si la columna se filta o no en el componente genérico de
     * consultas
     */
    private boolean filter;

    /**
     * Expresion en javascript que se ejecuta para conseguir el valor de la columna
     */
    private String expresion;

    /**
     * Tipo de regla que se utiliza para filtrar un campo
     */
    private TipoReglaFiltro tipoReglaFiltro;

    /**
     * Campo que almacena el parámetro utilizado para filtrar un campo de acuerdo
     * con el tipo de regla de filtrado que se utiliza. Por ejemplo, si el tipo de
     * campo es {{ TipoReglaFiltro.TEXT_CONTAINS }} entonces, en esta variable se
     * almacena el valor que debe contener el criterio de filtrado
     */
    private List<String> filtroValores = new ArrayList<>();

    /**
     * Lista de roles que pueden ver la columna
     */
    private List<RolAutoridad> roles = new ArrayList<>();
}
