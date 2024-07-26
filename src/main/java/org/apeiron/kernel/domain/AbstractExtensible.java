package org.apeiron.kernel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.BasicDBObject;
import java.util.Map;

/**
 * Agrega atrituto properties para estructura variable.
 */
public interface AbstractExtensible {
    /**
     * Objeto con las propiedades extras de la entidad.
     *
     * @return Propiedades extras de la entidad.
     */
    BasicDBObject getProperties();

    /**
     * Establece el objeto con las propiedades extras de la entidad.
     *
     * @param properties Popiedades extras de la entidad.
     */
    @JsonIgnore
    void setProperties(BasicDBObject properties);

    /**
     * Establece el objeto con las propiedades extras de la entidad.
     *
     * @param properties Popiedades extras de la entidad.
     */
    default void setProperties(Map<String, Object> properties) {
        if (properties != null) {
            setProperties(new BasicDBObject(properties));
        }
    }
}
