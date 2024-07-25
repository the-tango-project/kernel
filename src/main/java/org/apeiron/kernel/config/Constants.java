package org.apeiron.kernel.config;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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
        public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("d/MM/yyyy");

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

        public static final class Must {

            private Must() {}

            public static final String APOYO_UNICO = "apoyo-unico";
            public static final String PROPIETARIO_SOLICITUD = "propietario-solicitud";
            public static final String VALIDACION_FECHA = "fecha";
        }
    }

    public static final class SolicitanteConstants {

        private SolicitanteConstants() {}

        public static final String CORREO_VARIABLE_REGEX = "\\{\\{ solicitud.solicitante.correo \\}\\}";
        public static final String DEFAULT_SUBJECT_VARIABLE = "Mensaje informativo";
    }

    
    public static final class SolicitudConstants {

        private SolicitudConstants() {}

       
        /** Llaves para properties */
        public static final String PROPERTIES_KEY = "properties";
        /** Módulo de seguimiento de becas */
    }

    public static final class Mail {

        private Mail() {}

        public static final String CORREO_DEFAULT = "no-replay@mail.com";
        public static final String MAIL_CONTENT_PLACEHOLDER = "<!-- apeiron-mail-content -->";
    }

    public static final class SolucionConstants {

        private SolucionConstants() {}

        public static final String TIPO_CONVOCATORIA_PARAM_KEY = "tipoConvocatoria";
        public static final String ID_SOLUCIONES_BECAS = "idSolucionesBecas";
    }

    public static final class Documentos {

        private Documentos() {}

        public static final String DOCUMENTOS_KEY = "documentos";
        public static final String CONTENT_TYPE_KEY = "contentType";
        public static final String NOMBRE_KEY = "nombre";
        public static final String SIZE_KEY = "size";
        public static final String URI_KEY = "uri";
    }
    private Constants() {}
}
