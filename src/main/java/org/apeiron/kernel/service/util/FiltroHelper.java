package org.apeiron.kernel.service.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FiltroHelper {

    private static List<String> propertiesToExclude;

    static {
        List<String> defaultFilters = Arrays.asList("sort", "size", "page");
        propertiesToExclude = Arrays.stream(Filtro.class.getDeclaredFields()).map(Field::getName)
                .collect(Collectors.toList());
        propertiesToExclude.addAll(defaultFilters);
    }

    private FiltroHelper() {
    }

    /**
     * Método que mapea los Query Params diferentes a los definidos en la clase
     * Filtro y se los agrega en al propiedad
     * {@link Filtro#setAdditionalFilters(Map)} de tal manera que
     * se puedan procesar como filtros dinámicos dentro de la clas QueryHelper.
     *
     * @param filtro        con las propiedades base para filtrar una solicitud
     * @param requestParams con todos los QueryParams para procesar un filtrado
     * @return Filtro con los QueryParams adicionales
     */
    public static Filtro mapFiltersFromRequestParams(Filtro filtro, Map<String, String> requestParams) {
        filtro.setAdditionalFilters(mapQueryParams(requestParams));
        return filtro;
    }

    /**
     * Filtra los QueryParams que no se encuentran definidos en la clase Filtro y
     * los regresa en un nuevo Map
     *
     * @param requestParams con todos los QueryParams para procesar un filtrado
     * @return Map<String, String>
     */
    private static Map<String, String> mapQueryParams(Map<String, String> requestParams) {
        Map<String, String> props = new HashMap<>();
        for (Map.Entry<String, String> pair : requestParams.entrySet()) {
            if (!FiltroHelper.propertiesToExclude.contains(pair.getKey())) {
                props.put(pair.getKey(), pair.getValue());
            }
        }
        return props;
    }
}
