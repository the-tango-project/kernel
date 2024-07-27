package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.Data;
import org.apeiron.kernel.commons.annotations.ArgumentType;
import org.apeiron.kernel.commons.annotations.PropertyMapType;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.SolicitudDto;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Elemento de dominio que representa al tipo de argumento
 * {@link ArgumentType#PROPERTY_MAP}. Un propertyMap es un tipo de argumento
 * especial que define como se debe de mapear de un objeto hacia otro. En este
 * sentido, esta clase se utiliza para realizar operaciones que implican
 * convertir un objeto en otro o simplemente definir varibales que se copiaran
 * en un nuevo objeto. Un caso de uso conocido es cuando se emplea en un
 * {@link IAction} para convertir el objeto {@link SolicitudDto} en el objeto
 * {@link SolicitudDto} y enviar a un servicio externo una notificación
 * 
 * FIXME: Es posible que esta funcionalidad ya no se necesaria o se tenga que
 * constuir una más genérica
 *
 */
@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PropertyMap implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Tipo de mapeo representado por {@link PropertyMapType}
     */
    @Field("type")
    private PropertyMapType type;

    /**
     * variable que representa la ruta de donde se debe de extraer el
     * valor que se desea mapear
     */
    @Field("from")
    private String from;

    /**
     * variable que representa la ruta destino en donde se debe de copiar el valor
     * de la variable 'from'
     */
    @Field("to")
    private String to;

    /**
     * Cuando el tipo del mapeo es de {@link PropertyMapType#FROM_INLINE_VALUE} en
     * esta variable se guarda el valor que se debe de asignar a la variable 'to'
     */
    @Field("value")
    private String value;
}
