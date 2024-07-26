package org.apeiron.kernel.service.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Define un DTO con una estructura no fija.
 */
@Slf4j
@EqualsAndHashCode
public abstract class AbstractExtensibleDTO implements Serializable {

    @JsonIgnore
    private transient Map<String, Object> properties = new HashMap<>();

    /**
     * Método utilizado para cualquier propiedad que no exista en el modelo original.
     *
     * @param key
     * @param value
     */
    @JsonAnySetter
    @SuppressWarnings("unchecked")
    public void setProperty(String key, Object value) {
        if (key.equalsIgnoreCase("properties") && value instanceof Map) {
            log.debug("Se intentó agregar un mapa con llave 'properties' dentro de 'properties'");

            this.properties.putAll((Map<String, Object>) value);
        } else {
            properties.put(key, value);
        }
    }

    /**
     * @param properties Reemplaza las propiedades con las que tenga el request.
     */
    public void setProperties(Map<String, Object> properties) {
        if (properties != null) {
            this.properties = properties;
        }
    }

    /**
     * Método a invocar para recupear las propiedades que no existen en el modelo original.
     * @return
     */
    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }
}
