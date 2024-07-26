package org.apeiron.kernel.commons.annotations;

import org.apeiron.kernel.domain.PropertyMap;
import org.apeiron.kernel.domain.Solicitud;
import org.apeiron.kernel.domain.Solucion;
import org.apeiron.kernel.service.dto.PropertyMapDTO;

/**
 * Enumeración con los tipos de Mapeos que soporta Ápeiron a través del objecto
 * {@link PropertyMap} y {@link PropertyMapDTO}.
 *
 * @author Daniel Cortes Pichardo
 *
 */
public enum PropertyMapType {
    /**
     * Tipo de mapeo empleado para mapear propiedades de un objeto hacia otro.
     * Principalmente en ápeiron se utiliza para indicar que se deben de copiar
     * valores de propiedades que vienen en el objeto {@link Solicitud}
     */
    FROM_OBJECT,
    /**
     * Tipo de mapeo empleado para indicar que el valor de un objeto se debe de dar
     * directamente desde su configuración en lugar de extraerlo de algun otro
     * lugar. Este tipo de mapeo se utiliza para que el configurador de una solución
     * {@link Solucion} pueda indicar de manera directa el valor que deberá de tener
     * una determinada propiedad.
     */
    FROM_INLINE_VALUE,
}
