package org.apeiron.kernel.service.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;
import org.apeiron.kernel.domain.enumeration.EstadoSolucion;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.apeiron.kernel.domain.enumeration.TipoAcceso;
import org.apeiron.kernel.domain.enumeration.TipoSolucion;

/**
 * La clase filtro es utilizada para crear los criterios de busqueda que son
 * empleados para filtrar información en la colección de Solicitudes.
 * Principalmente, este objecto es utilizado a través de la clase
 * {@link org.apeiron.kernel.service.util.QueryHelper} para crear los
 * criterios de búsqueda. Cada propiedad definida aquí deberá de estar indexada
 * en la base de datos, de tal manera que las búsquedas se puedan ejecutar en
 * tiempo Logarítmico
 *
 * @author Equipo de arquitectura
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filtro implements Serializable {

    private String solicitudId;
    private String solucionId;
    private EstadoSolucion estadoSolucion;
    private EstadoSolicitud estadoSolicitud;
    // Tipo de acceso de la solución
    private List<TipoAcceso> tiposDeAcceso;
    private RolAutoridad rol;
    private String usuario;
    private String noSolicitudId;
    private String cvuAyudante;
    private String cvuSolicitante;
    private String noCvuSolicitante;
    private List<EstadoSolicitud> estadosSolicitud;
    private List<EstadoSolicitud> noEstadosSolicitud;
    private List<String> idsSolicitud;
    private TipoSolucion tipoSolucion;
    private Boolean solucionVisible;
    private String cuentaBancaria;
    private String tipoSolicitud;
    private List<String> idsSolucion;
    private String revisorId;
    private String evaluacionId;
    private String claveArea;
    private List<TipoSolucion> tiposSolucion;
    private Boolean esReconsideracion;
    private String claveInstitucion;
    private String nombreInstitucionNacional;
    private String nombreProgramaNacional;
    private String claveProgramaNacional;
    private String nombreSolicitante;
    private String apellidoPaternoSolicitante;
    private String apellidoMaternoSolicitante;
    private Boolean becaLiberada;

    /**
     * Filtros adicionales en la forma de <clave, valor>. La clave puede tomar
     * parametros de la forma <properties.programa.clave> que representan una ruta a
     * una propiedad dentor de la colección de Soluciones. Los valores siempre son
     * de tipo String. Esto filtros se configuran desde la solución y son dinámicos,
     * por lo tanto se deberá de considerar su indexación por separado
     *
     */
    private Map<String, String> additionalFilters;
}
