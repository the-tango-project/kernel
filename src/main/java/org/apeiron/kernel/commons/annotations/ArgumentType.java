package org.apeiron.kernel.commons.annotations;

import java.time.Instant;
import java.time.LocalDate;
import org.apeiron.kernel.service.dto.PropertyMapDTO;

/**
 * Enumeración con los tipos de datos que se pueden utilizar como argumento que
 * se pasa durante la aplicación de una regla o en la ejecución de un Post
 * Action
 *
 * Cada tipo de dato tiene su correspondiente elemento visual, de tal manera que
 * un tipo de dato STRING se verá como una caja de texto y uno de tipo DATE se
 * verá como un calendario. Esta el tipo de dato especial llamado PROPERTY_MAP
 * que se debe de utilizar cuando el postaction o la regla tenga que realizar un
 * mapeo del objeto solicitud a un objeto especifico
 *
 * @author Daniel Cortes Pichardo
 *
 */
public enum ArgumentType {
    /**
     * Argumento de tipo STRING. Un argumento de tipo STRING en java se representa
     * por {@link String}, mientras que en el front end se envia como una cadena de
     * texto
     */
    STRING(String.class),
    /**
     * Argumento de tipo INTEGER. Un argumento de tipo INTEGER en java se representa
     * por {@link Integer}, mientras que en el front end se envia como una cadena de
     * texto
     */
    INTEGER(Integer.class),
    /**
     * Argumento de tipo FLOAT. Un argumento de tipo FLOAT en java se representa
     * por {@link Float}, mientras que en el front end se envia como una cadena de
     * texto
     */
    FLOAT(Float.class),
    /**
     * Argumento de tipo DATE. Un argumento de tipo DATE en java se representa
     * por {@link LocalDate}, mientras que en el front end se envia como una cadena
     * de texto en formato yyyy-mm-dd
     */
    DATE(LocalDate.class),
    /**
     * Argumento de tipo DATE_TIME. Un argumento de tipo DATE_TIME en java se
     * representa por {@link Instant}, mientras que en el front end se envia como
     * una cadena de texto en formato UTC yyy-mm-ddTHH:mm:ss.SSSZ (ej.
     * 2023-12-02T01:35:53.961Z)
     * <a href="https://en.wikipedia.org/wiki/ISO_8601">Baeldung</a>
     */
    DATE_TIME(Instant.class),
    /**
     * Argumento de tipo PROPERTY_MAP. Un argumento de tipo PROPERTY_MAP en java se
     * representa por {@link PropertyMapDTO}
     */
    PROPERTY_MAP(PropertyMapDTO.class);

    private ArgumentType(Class<?> classType) {
        this.classType = classType;
    }

    private Class<?> classType;

    public Class<?> getClassType() {
        return this.classType;
    }
}
