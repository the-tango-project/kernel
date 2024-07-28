package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.Data;
import org.apeiron.kernel.commons.annotations.ArgumentType;
import org.apeiron.kernel.service.transition.TransitionElement;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Clase que es empleada para definir un argumento, y su tipo
 * {@link ArgumentType}. Los argumentos son parámetros que se le pueden pasar a
 * un {@link TransitionElement} para realizar su trabajo
 *
 */
@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Argument implements Serializable {

    /**
     * Tipo de argumento. Como valor predeterminado se dice que un
     * argumento es de tipo {@link ArgumentType#STRING}
     */
    @Field("type")
    private ArgumentType type;

    /**
     * Nombre del argumento
     */
    @Field("name")
    private String name;

    /**
     * Valor del argumento que esta delimitado por los valores definidos en
     * {@link ArgumentType#getClassType()}.
     */
    @Field("value")
    private Object value;
}
