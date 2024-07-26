package org.apeiron.kernel.service.util;

import static com.querydsl.core.types.ExpressionUtils.and;
import static java.util.Objects.isNull;
import static org.apeiron.kernel.utils.ValidationUtils.isArrayEmpty;
import static org.apeiron.kernel.utils.ValidationUtils.isNotEmpy;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import java.util.Map;
import org.apeiron.kernel.domain.QComentario;
import org.apeiron.kernel.domain.QRevision;
import org.apeiron.kernel.domain.QSolicitud;
import org.apeiron.kernel.domain.QSolucion;
import org.apeiron.kernel.domain.Respuesta;
import org.apeiron.kernel.domain.Revision;
import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.domain.bitacora.QBitacora;
import org.apeiron.kernel.domain.enumeration.TipoAcceso;
import org.apeiron.kernel.domain.enumeration.TipoSolucion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FIXME: Se deberá de adecuar esta clase para que sólo se utilicen consultas
 * sencillas
 */
public class QueryHelper {

    private static final Logger log = LoggerFactory.getLogger(QueryHelper.class);

    private QueryHelper() {
    }

    public static Predicate buildQuery(Filtro filtro) {
        QComentario q = QComentario.comentario;
        Predicate predicate = q.id.isNotNull();

        if (isNull(filtro)) {
            return predicate;
        }
        if (isNotEmpy(filtro.getSolicitudId())) {
            predicate = and(predicate, q.solicitudId.eq(filtro.getSolicitudId()));
        }

        if (isNotEmpy(filtro.getSolucionId())) {
            predicate = and(predicate, q.solicitudId.eq(filtro.getSolucionId()));
        }

        log.debug("Predicate : {}", predicate);
        return predicate;
    }

    public static Predicate buildSolucionQuery(Filtro filtro) {
        QSolucion qSolucion = QSolucion.solucion;
        Predicate predicate = qSolucion.id.isNotNull();

        if (isNull(filtro)) {
            return predicate;
        }

        if (isNotEmpy(filtro.getEstadoSolucion())) {
            predicate = and(predicate, qSolucion.estado.eq(filtro.getEstadoSolucion()));
        }

        if (isNotEmpy(filtro.getSolucionId())) {
            predicate = and(predicate, qSolucion.id.eq(filtro.getSolucionId()));
        }

        if (isNotEmpy(filtro.getTipoSolucion())) {
            predicate = and(predicate, qSolucion.tipo.eq(filtro.getTipoSolucion()));
        }

        if (isNotEmpy(filtro.getSolucionVisible())) {
            predicate = and(predicate, qSolucion.visible.eq(filtro.getSolucionVisible()));
        }

        if (isNotEmpy(filtro.getUsuario())) {
            predicate = and(predicate, qSolucion.autoridades.any().usuarioId.eq(filtro.getUsuario()));
        }

        if (!isNull(filtro.getTiposSolucion()) && isNotEmpy(filtro.getTiposSolucion())) {
            predicate = and(predicate, qSolucion.tipo.in(filtro.getTiposSolucion()));
        }

        predicate = filterByTipoDeAcceso(filtro, qSolucion, predicate);

        log.debug("Predicate : {}", predicate);
        return predicate;
    }

    public static Predicate filterByTipoDeAcceso(Filtro filtro, QSolucion qSolucion, Predicate predicate) {
        if (isArrayEmpty(filtro.getTiposDeAcceso())) {
            return predicate;
        }

        if (filtro.getTiposDeAcceso().contains(TipoAcceso.PUBLICO)) {
            filtro.getTiposDeAcceso().add(null);
        }

        return and(predicate, qSolucion.tipoAcceso.in(filtro.getTiposDeAcceso()));
    }

    public static Predicate buildSolicitudQuery(Filtro filtro) {
        QSolicitud qSolicitud = QSolicitud.solicitud;
        Predicate predicate = qSolicitud.id.isNotNull();

        if (isNull(filtro)) {
            return predicate;
        }

        if (isNotEmpy(filtro.getUsuario())) {
            predicate = filterByUsuario(filtro, qSolicitud, predicate);
        }

        if (isNotEmpy(filtro.getSolicitudId())) {
            predicate = and(predicate, qSolicitud.id.eq(filtro.getSolicitudId()));
        }

        if (isNotEmpy(filtro.getNoSolicitudId())) {
            predicate = and(predicate, qSolicitud.id.ne(filtro.getNoSolicitudId()));
        }

        if (isNotEmpy(filtro.getSolucionId())) {
            predicate = and(predicate, qSolicitud.solucionId.eq(filtro.getSolucionId()));
        }

        if (isNotEmpy(filtro.getEstadoSolicitud())) {
            predicate = and(predicate, qSolicitud.estado.eq(filtro.getEstadoSolicitud()));
        }

        if (!isNull(filtro.getEstadosSolicitud()) && !filtro.getEstadosSolicitud().isEmpty()) {
            predicate = and(predicate, qSolicitud.estado.in(filtro.getEstadosSolicitud()));
        }

        if (!isNull(filtro.getCvuSolicitante())) {
            String[] cvus = filtro.getCvuSolicitante().split(",");
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud").getString("solicitante.cvu");
            predicate = and(predicate, pathSolicitud.in(cvus));
        }

        if (!isNull(filtro.getNoCvuSolicitante())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud").getString("solicitante.cvu");
            predicate = and(predicate, pathSolicitud.ne(filtro.getNoCvuSolicitante()));
        }

        if (isNotEmpy(filtro.getCvuAyudante())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.ayudante.cvu");
            predicate = and(predicate, pathSolicitud.eq(filtro.getCvuAyudante()));
        }

        if (!isNull(filtro.getNoEstadosSolicitud()) && !filtro.getNoEstadosSolicitud().isEmpty()) {
            predicate = and(predicate, qSolicitud.estado.notIn(filtro.getNoEstadosSolicitud()));
        }

        if (!isNull(filtro.getIdsSolicitud()) && !filtro.getIdsSolicitud().isEmpty()) {
            predicate = and(predicate, qSolicitud.id.in(filtro.getIdsSolicitud()));
        }

        if (!isNull(filtro.getIdsSolucion()) && !filtro.getIdsSolucion().isEmpty()) {
            predicate = and(predicate, qSolicitud.solucionId.in(filtro.getIdsSolucion()));
        }

        if (isNotEmpy(filtro.getCuentaBancaria())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.cuentaBancaria");
            predicate = and(predicate, pathSolicitud.eq(filtro.getCuentaBancaria()));
        }

        if (isNotEmpy(filtro.getTipoSolicitud())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.SOLICITUD_RIZOMA.tipoSolicitud.id");
            predicate = and(predicate, pathSolicitud.eq(filtro.getTipoSolicitud()));
        }

        if (isNotEmpy(filtro.getClaveArea())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.SOLICITUD_RIZOMA.areaConocimiento.area.clave");
            predicate = and(predicate, pathSolicitud.eq(filtro.getClaveArea()));
        }

        if (!isNull(filtro.getAdditionalFilters()) && !filtro.getAdditionalFilters().isEmpty()
                && !isAdditionalAcreditacion(filtro)) {
            for (Map.Entry<String, String> propiedadFiltrada : filtro.getAdditionalFilters().entrySet()) {
                StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                        .getString(propiedadFiltrada.getKey());
                predicate = and(predicate, pathSolicitud.eq(propiedadFiltrada.getValue()));
            }
        }

        if (Boolean.TRUE.equals(filtro.getEsReconsideracion())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.reconsideracion.uri");
            predicate = and(predicate, pathSolicitud.isNotNull());
        }

        if (!isNull(filtro.getAdditionalFilters()) && !filtro.getAdditionalFilters().isEmpty()
                && isAdditionalAcreditacion(filtro)) {
            for (Map.Entry<String, String> propiedadFiltrada : filtro.getAdditionalFilters().entrySet()) {
                if (!"cvuAprobador".equals(propiedadFiltrada.getKey())) {
                    if ("informeMensual".equals(propiedadFiltrada.getKey())) {
                        StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                                .getString("properties.list");
                        predicate = and(predicate, pathSolicitud.isNotNull());
                    } else if ("estadoDocComprobante".equals(propiedadFiltrada.getKey())
                            || "estadoDocInforme".equals(propiedadFiltrada.getKey())) {
                        StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                                .getString("properties." + propiedadFiltrada.getKey());
                        predicate = and(predicate, pathSolicitud.eq(propiedadFiltrada.getValue()));
                    } else {
                        NumberPath<Integer> pathSolicitudInt = new PathBuilder<>(Solicitud.class, "solicitud")
                                .getNumber("properties.institucionActualSubSeccion." + propiedadFiltrada.getKey(),
                                        Integer.class);
                        predicate = and(predicate, pathSolicitudInt.eq(Integer.parseInt(propiedadFiltrada.getValue())));
                    }
                }
            }
        }

        if (isNotEmpy(filtro.getClaveInstitucion())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.programa.instituciones.claveInstitucion");
            predicate = and(predicate, pathSolicitud.eq(filtro.getClaveInstitucion()));
        }

        if (isNotEmpy(filtro.getNombreInstitucionNacional())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.SOLICITUD_RIZOMA.programa.participante.institucion.nombreInstitucion");
            predicate = and(predicate, pathSolicitud.eq(filtro.getNombreInstitucionNacional()));
        }

        if (isNotEmpy(filtro.getNombreProgramaNacional())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.SOLICITUD_RIZOMA.programa.participante.programa.nombre");
            predicate = and(predicate, pathSolicitud.eq(filtro.getNombreProgramaNacional()));
        }

        if (isNotEmpy(filtro.getClaveProgramaNacional())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.SOLICITUD_RIZOMA.programa.participante.programa.clave");
            predicate = and(predicate, pathSolicitud.eq(filtro.getClaveProgramaNacional()));
        }

        if (isNotEmpy(filtro.getNombreSolicitante())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud").getString("solicitante.nombre");
            predicate = and(predicate, pathSolicitud.eq(filtro.getNombreSolicitante()));
        }

        if (isNotEmpy(filtro.getApellidoPaternoSolicitante())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("solicitante.apellidoPaterno");
            predicate = and(predicate, pathSolicitud.eq(filtro.getApellidoPaternoSolicitante()));
        }

        if (isNotEmpy(filtro.getApellidoMaternoSolicitante())) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("solicitante.apellidoMaterno");
            predicate = and(predicate, pathSolicitud.eq(filtro.getApellidoMaternoSolicitante()));
        }

        if (isNotEmpy(filtro.getBecaLiberada())) {
            BooleanPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getBoolean("properties.becaLiberada");
            predicate = (Boolean.FALSE.equals(filtro.getBecaLiberada()))
                    ? and(predicate, pathSolicitud.isNull().or(pathSolicitud.isFalse()))
                    : and(predicate, pathSolicitud.eq(filtro.getBecaLiberada()));
        }

        log.debug("Predicate : {}", predicate);
        return predicate;
    }

    public static Predicate buildBitacoraQuery(Filtro filtro) {
        QBitacora qBitacora = QBitacora.bitacora;
        Predicate predicate = qBitacora.id.isNotNull();

        if (isNull(filtro)) {
            return predicate;
        }

        if (isNotEmpy(filtro.getSolicitudId())) {
            predicate = and(predicate, qBitacora.solicitudId.eq(filtro.getSolicitudId()));
        }

        if (isNotEmpy(filtro.getEstadoSolicitud())) {
            predicate = and(predicate, qBitacora.estadoFinal.eq(filtro.getEstadoSolicitud()));
        }

        log.debug("Predicate : {}", predicate);

        return predicate;
    }

    public static Predicate buildRevisionQuery(FiltroRevision filtro) {
        QRevision q = QRevision.revision;
        Predicate predicate = q.id.isNotNull();

        if (isNull(filtro)) {
            return predicate;
        }

        if (!isNull(filtro.getEvaluacionId())) {
            predicate = and(predicate, q.evaluacionId.eq(filtro.getEvaluacionId()));
        }

        if (!isNull(filtro.getRevisorId())) {
            StringPath revisorId = new PathBuilder<>(Revision.class, "revision").getString("revisor.revisor_id");
            predicate = and(predicate, revisorId.eq(filtro.getRevisorId()));
        }

        if (!isNull(filtro.getEstado())) {
            predicate = and(predicate, q.estado.eq(filtro.getEstado()));
        }

        if (!isNull(filtro.getSolucionId())) {
            predicate = and(predicate, q.solicitudResumen.solucionId.eq(filtro.getSolucionId()));
        }

        if (!isNull(filtro.getComision())) {
            StringPath respuesta = new PathBuilder<>(Respuesta.class, "respuesta")
                    .getString("respuesta.properties.comision");
            predicate = and(predicate, respuesta.eq(filtro.getComision()));
        }

        if (!isNull(filtro.getSolicitudId())) {
            predicate = and(predicate, q.solicitudResumen.solicitudId.eq(filtro.getSolicitudId()));
        }

        if (!isNull(filtro.getRevisoresId()) && !filtro.getRevisoresId().isEmpty()) {
            StringPath revisorId = new PathBuilder<>(Revision.class, "revision").getString("revisor.revisor_id");
            predicate = and(predicate, revisorId.in(filtro.getRevisoresId()));
        }

        log.debug("Predicate : {}", predicate);
        return predicate;
    }

    /**
     * Método que filtra únicamente a los usuarios que tienen permisos de algún tipo
     * a la solicitud. Estos permisos pueden
     * ser de lectura o escritura
     *
     * @param filtro
     * @param qSolicitud
     * @param predicate
     * @return
     */
    private static Predicate filterByUsuario(Filtro filtro, QSolicitud qSolicitud, Predicate predicate) {
        BooleanExpression usuarioPredicate = qSolicitud.usuario.eq(filtro.getUsuario());

        if (isNotEmpy(filtro.getTipoSolucion()) && filtro.getTipoSolucion().equals(TipoSolucion.AYUDANTES)) {
            StringPath pathSolicitud = new PathBuilder<>(Solicitud.class, "solicitud")
                    .getString("properties.ayudante.login");
            usuarioPredicate = usuarioPredicate.or(pathSolicitud.eq(filtro.getUsuario()));
        }
        return and(predicate, usuarioPredicate);
    }

    private static boolean isAdditionalAcreditacion(Filtro filtro) {
        if (!isNull(filtro.getAdditionalFilters()) && !filtro.getAdditionalFilters().isEmpty()) {
            for (Map.Entry<String, String> propiedadFiltrada : filtro.getAdditionalFilters().entrySet()) {
                if ("idInstitucion".equals(propiedadFiltrada.getKey()) ||
                        "estadoDocComprobante".equals(propiedadFiltrada.getKey()) ||
                        "estadoDocInforme".equals(propiedadFiltrada.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

}
