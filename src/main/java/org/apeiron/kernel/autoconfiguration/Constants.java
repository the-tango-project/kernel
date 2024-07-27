package org.apeiron.kernel.autoconfiguration;

import static org.apeiron.kernel.domain.enumeration.EstadoSolicitud.CANCELADA;
import static org.apeiron.kernel.domain.enumeration.EstadoSolicitud.FORMALIZADA;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apeiron.kernel.domain.enumeration.EstadoSolicitud;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "es";
    public static final String CHECK_KEY = "check";
    public static final ZoneId ZONA_MEXICO = ZoneId.of("UTC-6");
    public static final DateTimeFormatter FORMATO_MEXICO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final int CURRENT_YEAR = LocalDate.now().getYear();
    public static final Boolean RULE_PASSED = true;
    public static final Boolean RULE_FAILED = false;

    public static final class Apeiron {

        private Apeiron() {}

        public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public static final List<EstadoSolicitud> VALID_SOLICITUD_STATE = Collections.unmodifiableList(
            Arrays.asList(CANCELADA, FORMALIZADA)
        );
        public static final ZoneOffset OFFSET_UTC = ZoneOffset.UTC;
    }

    public static final class TextFormat {

        private TextFormat() {}

        public static final String BLANK = "";
        public static final String SPACE = " ";
        public static final String DOT = ".";
        public static final String TWO_STRINGS_SEPARATOR_DOT = "%s.%s";
        public static final String TWO_STRINGS_SEPARATOR_SPACE = "%s %s";
        public static final String THREE_STRINGS_SEPARATOR_SPACE = "%s %s %s";
    }

    public static final class Html {

        private Html() {}

        public static final String STRONG_OPEN = "<strong>";
        public static final String STRONG_CLOSE = "</strong>";
    }

    public static final class RuleTag {

        private RuleTag() {}

        public static final String BECAS = "becas";
        public static final String BECAS_NACIONALES = "nacionales";
        public static final String BECAS_EXTRANJERO = "extranjero";
        public static final String BECAS_VINCULACION = "vinculacion";
        public static final String BECAS_POSDOCTORADO = "posdoctorado";
        public static final String ACREDITACIONES = "acreditaciones";

        public static final String SNI = "sni";
        public static final String SNI_AYUDANTES = "ayudantes";
        public static final String SNI_INGRESO_PERMANENCIA = "ingreso-permanencia";
        public static final String SNI_CUENTAS_BANCARIAS = "cuentas-bancarias";
        public static final String DOCUMENTOS = "documentos";
        public static final String SNI_VIGENCIAS = "sni-vigencias";
        public static final String SNP = "snp";

        public static final class Must {

            private Must() {}

            public static final String APOYO_UNICO = "apoyo-unico";
            public static final String PROPIETARIO_SOLICITUD = "propietario-solicitud";
            public static final String UMAS_DISPONOBLES_INVESTIGADOR = "umas-disponibles";
            public static final String ESTUDIANTES_REGISTRADOS = "estudiantes-registrados";
            public static final String PROGRAMA_ELEGIBLE = "programa-elegible";
            public static final String VALIDACION_FECHA = "fecha";
        }
    }

    public static final class SolicitanteConstants {

        private SolicitanteConstants() {}

        public static final String NIVEL_SNI_III = "Investigador Nacional  Nivel III";
        public static final String NIVEL_SNI_EMERITO = "Investigador Nacional Emérito";
        public static final String CORREO_VARIABLE_REGEX = "\\{\\{ solicitud.solicitante.correo \\}\\}";
        public static final String DEFAULT_SUBJECT_VARIABLE = "Mensaje informativo";
    }

    public static final class AyudantesConstants {

        private AyudantesConstants() {}

        public static final int LIMITE_UMAS_POR_INVESTIGADOR = 3;
        public static final String FECHA_SOLICITUD_RECLASIFICACION = "fechaSolicitudReclasificacion";
        public static final String FECHA_EFECTIVA_RECLASIFICACION = "fechaEfectivaReclasificacion";
        public static final String CLAVE_FORMALIZACION_ALTA = "SNI-AYUD-ALTA-2023"; // Clave de proceso de alta
        public static final String CLAVE_FORMALIZACION_RCLS = "SNI-AYUD-RCLS-2023"; // Clave de proceso de
        // reclasificación

        public static final String CLAVE_FORMALIZACION_BAJA_OPERADOR = "SNI-AYUD-BCON-2023";
        public static final String CLAVE_FORMALIZACION_BAJA_SOLICITANTE = "SNI-AYUD-BSNI-2023";
        public static final String CLAVE_FORMALIZACION_BAJA_AYUDANTE = "SNI-AYUD-BAYU-2023";
        public static final String CLAVE_FORMALIZACION_BAJA_POR_ACUERDO = "SNI-AYUD-BMAC-2023";

        public static final String FECHA_INICIO_RETROACTIVO = "fechaInicioRetroactivo";
        public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("d/MM/yyyy");

        /** Llaves para properties */
        public static final String ITERACION_KEY = "iteracion";
        public static final String FECHA_INICIO_KEY = "fecha_inicio";
        public static final String FECHA_FIN_KEY = "fecha_fin";
        public static final String AYUDANTE_KEY = "ayudante";
        public static final String UMA_KEY = "uma";
        public static final String UMA_ANTERIOR_KEY = "uma_anterior";
        public static final String EXPEDIENTE_KEY = "expediente";
        public static final String JOB_KEY = "jobId";

        /** Mensajes de error */
        public static final String MENSAJE_ERROR_AGREGAR_EXPEDIENTE = "Error al enviar ayudante a Expediente: {}";
    }

    public static final class CuentaBancariaConstants {

        private CuentaBancariaConstants() {}

        public static final String MENSAJE_ERROR_AGREGAR_EXPEDIENTE =
            "El servicio de agregar cuenta bancaria en Histórico no está disponible.";
    }

    public static final class SolicitudConstants {

        private SolicitudConstants() {}

        public static final String FECHA_SOLICITUD_BAJA = "fechaSolicitudBaja";
        public static final String FECHA_EFECTIVA_BAJA = "fechaEfectivaBaja";
        public static final String CUENTA_BANCARIA = "cuentaBancaria";
        public static final String BANCO = "banco";
        public static final int LONGITUD_CUENTA_BANCARIA = 18;
        public static final String DEC_VERACIDAD = "veracidad";
        /** Llaves para properties */
        public static final String PROPERTIES_KEY = "properties";
        public static final String SOLICITUD_RIZOMA_KEY = "SOLICITUD_RIZOMA";
        /** Módulo de seguimiento de becas */
        public static final String BECA_LIBERADA_KEY = "becaLiberada";
    }

    public static final class BecaConstants {

        private BecaConstants() {}

        public static final String SOLICITUD_ID = "solicitudId";
    }

    public static final class AcreditacionesConstants {

        private AcreditacionesConstants() {}

        public static final String CLAVE_FORMALIZACION_INFORME_MENSUAL_EXT = "SNI-ACRED-INFORME-EXT-2023"; // Informe
        // mensual
        // para
        // usuarios
        // externos
        public static final String CLAVE_FORMALIZACION_INFORME_MENSUAL = "SNI-ACRED-INFORME-2023"; // Informe mensual
        // para usuarios
        // internos
        public static final String CLAVE_FORMALIZACION_INFORME_MENSUAL_SINT = "SNI-ACRED-INFORME-SIN-INST-2023"; // Informe
        // mensual
        // para
        // los
        // que
        // no
        // tienen
        // institucion
        public static final String CLAVE_FORMALIZACION_ACRED = "SNI-ACRED-2023"; // comprobante de acreditacion usuario
        // interno
        public static final String CLAVE_FORMALIZACION_ACRED_EXT = "SNI-ACRED-EXT-2023"; // comprobante de acreditacion
        // para usuario externos
        public static final String CLAVE_FORMALIZACION_ACRED_SIN_INST = "SNI-ACRED-SIN-INST-2023"; // comprobante de
        // acreditacion para
        // usuarios internos
        // sin institucion
        public static final String DESC_SITUACION_SIN_ACREDITACION = "Sin institución";
        public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-d");

        public static final String COMPROBANTE_ACREDITACION = "COM";
        public static final String INFORME_MENSUAL = "INF";

        /** Llaves para properties */
        public static final String ITERACION_KEY = "iteracion";
        public static final String ITERACION_INFORME_KEY = "iteracionInforme";
        public static final String ESTATUS_INFOME_MENSUAL_KEY = "estatus_informe_mensual";

        public static final String FECHA_INICIO_KEY = "fechaInicioSituacion";
        public static final String FECHA_FIN_KEY = "fechaFinSituacion";

        public static final String CVU_APROBADOR_KEY = "cvu_aprobador";
        public static final String CVU_AUTORIDAD_FACULTADA_KEY = "cvu_autoridad_facultada";
        public static final String INSTITUCION_APROBADOR_KEY = "institucion_aprobador";
        public static final String TIPO_ACREDITACION_KEY = "tipoAcreditacion";

        public static final String FECHA_REGISTRO_KEY = "fechaRegistro";
        public static final String ESTADO_ENTIDAD_KEY = "entidadSituacionActual";

        public static final String POST_ACTUALIZAR_ACREDITACION_KEY = "ActualizarAcreditacionAFormalizada";

        /* LLave de autoridad facultada */
        public static final String AUTORIDAD_FACULTADA = "Autoridad facultada";
        public static final Long AUTORIDAD_FACULTADA_ID = 1L;

        /* Llava de propiedrades del form */
        public static final String FORM_INSTITUCION_ACTUAL_SUB = "institucionActualSubSeccion";
        public static final String FORM_INSTITUCION_ID = "idInstitucion";
        public static final String FORM_DEPENDENCIA_ID = "idDependencia";
        public static final String FORM_SUBDEPENDENCIA_ID = "idSubDependencia";
        public static final String FORM_DEPARTAMENTO_ID = "idDepartamento";
        public static final String FORM_INSTITUCION_ID_COMISION = "idInstitucionComision";
        public static final String FORM_DEPENDENCIA_ID_COMISION = "idDependenciaComision";
        public static final String FORM_SUBDEPENDENCIA_ID_COMISION = "idSubDependenciaComision";
        public static final String FORM_DEPARTAMENTO_ID_COMISION = "idDepartamentoComision";

        public static final String FORM_INSTITUCION = "institucion";
        public static final String FORM_DEPENDENCIA = "dependencia";
        public static final String FORM_SUBDEPENDENCIA = "subDependencia";
        public static final String FORM_DEPARTAMENTO = "departamento";
        public static final String FORM_INSTITUCION_COMISION = "institucionComision";
        public static final String FORM_DEPENDENCIA_COMISION = "dependenciaComision";
        public static final String FORM_SUBDEPENDENCIA_COMISION = "subDependenciaComision";
        public static final String FORM_DEPARTAMENTO_COMISION = "departamentoComision";

        public static final String TIPO_NIVEL_ID = "idTipoNivel";
        public static final String PAIS_ID = "idPais";

        public static final String TIPO_NIVEL_ID_COMISION = "idTipoNivelComision";
        public static final String PAIS_ID_COMISION = "idPaisComision";

        public static final Integer PUBLICA = 1;
        public static final Integer MEXICO = 143;

        public static final Integer NACIONAL = 1;
        public static final Integer EXTRANJERA = 2;

        public static final String REMUNERACION = "remuneracionNuevaSituacion";
        public static final String ENTIDAD = "estado";
        public static final String ENTIDAD_COMISION = "estadoComision";
        public static final String ACTIVO_Y_VIGENTE = "activoYVigenteNuevaSituacion";
        public static final String SITUACIONES = "situaciones";
        public static final String TIPO_PARTICIPACION = "tipoParticipacion";

        /* Constante tipo Guardado Institucion */
        public static final Long POSICION_ACTUAL = 1l;
        public static final Long POSICION_ACTUAL_Y_NIVELES = 2l;
        public static final String CONTEO_SITUACIONES = "Total de registros reportados";

        /** Llaves de propiedades de objecto de formalizacion solicitudDto */
        public static final String LIST_SOLICITUDES_KEY = "list";
        public static final String CLAVE_INFORME_KEY = "claveInforme";
        public static final String CONTEO_SITUACIONES_KEY = "conteoSituaciones";
        public static final String ORIGINAL_KEY = "originalId";
        public static final String SOLUCION_KEY = "solucionId";
        public static final String ACCION_RETORNO_KEY = "accionRetorno";
        public static final String FECHA_EMISION_KEY = "fechaEmision";
        public static final String HEADER_INSTITUCION_KEY = "headerInstitucion";
        public static final String HEADER_DEPENDENCIA_KEY = "headerDependencia";
        public static final String HEADER_SUBDEPENDENCIA_KEY = "headerSubdependencia";
        public static final String HEADER_DEPARTAMENTO_KEY = "headerDepartamento";

        /** Constante para nivel de institucion */
        public static final Long NIVEL_INSTITUCION = 1l;
        public static final Long NIVEL_DEPENDENCIA = 2l;
        public static final Long NIVEL_SUBDEPENDENCIA = 3l;
        public static final Long NIVEL_DEPARTAMENTO = 4l;

        public static final String URL_CONVENIO = "urlComprobante";
        public static final String URL_INFORME = "urlInforme";
        public static final String URL = "uri";
        public static final String FECHA_FORMALIZACION = "fechaFormalizacion";

        public static final String ESTADO_DOC_COMPROBANTE = "estadoDocComprobante";
        public static final String ESTADO_DOC_INFORME = "estadoDocInforme";
        public static final String ESTADO_DOC_SIN_GENERAR = "SIN GENERAR";

        /** Tipos de situaciones */

        public static final String BAJA_INSTITUCION = "Baja de la institución";
        public static final String JUBILACION = "Jubilación";
        public static final String COMISION_CON_SUELDO = "Comisión o licencia con goce de sueldo";
        public static final String COMISION_PARCIAL_SUELDO = "Comisión o licencia con goce parcial de sueldo";
        public static final String PERSONA_ACTIVO_DETERMINADO = "Personal por tiempo determinado";
        public static final String PERSONA_ACTIVO_INDETERMINADO = "Personal por tiempo indeterminado";
        public static final String BAJA_FALLECIMIENTO = " Baja por fallecimiento";
        public static final String CARGO_ADMINISTRATIVO = "Cargo administrativo";

        public static final String MENSAJE_PUBLICACION = "mensajePublicacion";

        public static final String CLAVE_BAJA_INSTITUCION = "BAJINST";
        public static final String CLAVE_JUBILACION = "JUBILA";
        public static final String CLAVE_COMISION_CON_SUELDO = "COMCSDO";
        public static final String CLAVE_COMSION_PARCIAL_SUELDO = "COMPSDO";
        public static final String CLAVE_PERSONAL_ACTIVO_DETERMINADO = "CTOTDET";
        public static final String CLAVE_PERSONAL_ACTIVO_INDETERMINADO = "CTOTIND";
        public static final String CLAVE_BAJA_FALLECIMIENTO = "FALLEC";
        public static final String CLAVE_CARGO_ADMINISTRATIVO = "CARGO-ADM";
        public static final String CLAVE_SIN_INSTITUCION = "SINADSC";

        public static final String JOB_KEY = "jobId";
        public static final String JOB_OPERACION_EN_PROCESO_KEY = "jobOperacionProceso";
        public static final String JOB_FORMALIZADA_KEY = "jobFormalizada";

        public static final String EXPEDIENTE_KEY = "expediente";
    }

    /*
     * Constantes para el proceso de becas
     */
    public static final class BecaNacionalesConstants {

        private BecaNacionalesConstants() {}

        public static final double UMAS_DOCTORADO = 6.0;

        public static final double UMAS_MAESTRIA = 4.5;

        public static final double UMAS_ESPECIALIDAD = 4.0;

        public static final double VALOR_UMAS = 3153.70;

        public static final String TITULO_FORMALIZACION = "BECA NACIONAL TRADICIONAL";
    }

    /**
     * Constantes para el proceso de ingreso, permanencia y eméritos.
     */
    public static final class IngresoPermanenciaConstants {

        private IngresoPermanenciaConstants() {}

        public static final String CLAVE_FORMALIZACION_ALTA = "SNI-RECON";

        public static final String ID_EVALUACION_NO_APROBADO = "5";

        public static final String CLAVE_FORMALIZACION_ALTA_EMER = "SNI-RECON-EMER";

        public static final String PROCESO_CLAVE_OFI = "OFI";

        public static final String PROCESO_CLAVE_OFI_MOD_EME = "OFIM";

        public static final String PROCESO_CLAVE_REC = "REC";

        public static final String SOLICITUD_ID_KEY = "solicitud";

        public static final String CLAVE_FORMALIZACION_ALTA_RECONSID = "SNI-RECON-RECONSID";
    }

    public static final class Mail {

        private Mail() {}

        public static final String CORREO_DEFAULT = "no-replay@mail.com";
        public static final String MAIL_CONTENT_PLACEHOLDER = "<!-- apeiron-mail-content -->";
    }

    public static final class VigenciaSni {

        private VigenciaSni() {}

        public static final String CLAVE_TIPO_VIGENCIA_FALLECIMIENTO = "8";
        public static final String CLAVE_TIPO_VIGENTE_EN_SUSPENCION_ART_77 = "17";
        public static final String CLAVE_TIPO_APLICA_CARGO_ADMINISTRACION_DE_ELECCION_POPULAR = "24";
        public static final String CLAVE_TIPO_VIGENCIA_EMERITO = "99";
        public static final String CLAVE_TIPO_VIGENCIA_BAJA_JUNTA_HONOR = "51";
        public static final String CLAVE_NIVEL_SNI_SEIS = "6";
        public static final String CLAVE_TIPO_EVALUACION = "0";
        public static final String CLAVE_TIPO_EVALUACION_REVISORA = "26";

        public static final String CLAVE_ESTATUS_INACTIVO = "ESTATUS_INACTIVO";
        public static final String CLAVE_ESTATUS_POR_ACTIVAR = "POR_ACTIVAR";
        public static final String CLAVE_ESTATUS_CANCELADO_POR_SOLICITUD_RECONSIDERACION = "CANCELADO_POR_SOLICITUD_RECONSIDERACION";
        public static final String CLAVE_ESTATUS_NO_APROBADO_CONSEJO_GENERAL = "NO_APROBADO_CONSEJO_GENERAL";
        public static final String CLAVE_ESTATUS_VIGENCIA_ACTIVO = "ACTIVO";
    }

    public static final class SolucionConstants {

        private SolucionConstants() {}

        public static final String TIPO_CONVOCATORIA_PARAM_KEY = "tipoConvocatoria";
        public static final String ID_SOLUCIONES_BECAS = "idSolucionesBecas";
    }

    public static final class MensajeConstants {

        private MensajeConstants() {}

        public static final String SERVICIO_EXPEDIENTE_NO_DISPONBILE = "El servicio de expediente no está disponible";

        public static final String SERVICIO_FIRMA_CURP_NO_DISPONBILE = "El servicio de firma curp no está disponible";

        public static final String SERVICIO_VALIDAR_CURP_NO_DISPONBILE = "El servicio de validar curp no está disponible";

        public static final String SERVICIO_PERFIL_USUARIO_NO_DISPONBILE = "El servicio de perfil de usuario no está disponible";

        public static final String EXPEDIENTE_NO_ENCONTRADO = "Expediente no encontrado con número de cvu %s";

        public static final String ERROR_OBTENER_EXPEDIENTE = "Ocurrió un error al obtener el expediente";

        public static final String ERROR_ENVIAR_VIGENCIA =
            "Ocurrió un error al agregar o modificar la vigencia al expediente con número de cvu %s";
    }

    public static final class NivelSni {

        private NivelSni() {}

        public static final String CLAVE_CANDIDATO = "C";
        public static final String CLAVE_NO_APROBADO = "N";
        public static final String CLAVE_I = "1";
        public static final String CLAVE_II = "2";
        public static final String CLAVE_III = "3";
        public static final String CLAVE_EMERITO = "E";
    }

    public static final class Documentos {

        private Documentos() {}

        public static final String DOCUMENTOS_KEY = "documentos";
        public static final String CONTENT_TYPE_KEY = "contentType";
        public static final String NOMBRE_KEY = "nombre";
        public static final String SIZE_KEY = "size";
        public static final String URI_KEY = "uri";
    }

    public static final class EstadoVigencia {

        private EstadoVigencia() {}

        public static final String ACTIVO = "ACTIVO";
        public static final String INACTIVO = "INACTIVO";
        public static final String PENDIENTE = "PENDIENTE";

        public static final String CANCELADO = "CANCELADO";
    }

    /*
     * Constantes para el proceso de SNP
     */
    public static final class SNPConstants {

        public static final String PROGRAMA_ELEGIBLE = "ELEGIBLE";
    }

    /*
     * Constantes para el entity Beca
     */public static final class Beca {

        private Beca() {}

        public static final String BECARIO_NOMBRE = "becario.nombre";
        public static final String BECARIO_APELLIDO_PATERNO = "becario.apellidoPaterno";
        public static final String BECARIO_APELLIDO_MATERNO = "becario.apellidoMaterno";
        public static final String PROGRAMA_CLAVE = "programa.clave";
        public static final String PROGRAMA_NOMBRE = "programa.nombre";
        public static final String PROGRAMA_ORIENTACION = "programa.orientacion";
        public static final String PROGRAMA_MODALIDAD = "programa.modalidad";
        public static final String PROGRAMA_INSTITUCION_NOMBRE = "programa.institucion.nombreInstitucion";
        public static final String PROGRAMA_SEDE_CLAVE = "programa.sede.claveSede";
    }

    /*
     * Constantes para el entity BitacoraLiberacionBecas
     */public static final class BitacoraLiberacionBeca {

        private BitacoraLiberacionBeca() {}

        public static final String SOLUCION_ID = "solucionId";
        public static final String NOMBRE_COMPLETO_USUARIO = "nombreCompletoUsuario";
        public static final String USUARIO = "usuario";
    }

    /*
     * Constantes para properties
     */public static final class Properties {

        private Properties() {}

        public static final String PARAMS = "params";
        public static final String CLAVE_FORMALIZACION = "claveFormalizacion";
        public static final String TITULO_FORMALIZACION = "tituloFormalizacion";
    }

    private Constants() {}
}
