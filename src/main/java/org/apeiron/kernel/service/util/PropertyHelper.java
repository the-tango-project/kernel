package org.apeiron.kernel.service.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PropertyHelper {

    public static final String PROGRAMA_PROPERTY = "programa";
    public static final DateTimeFormatter SOURCE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TARGET_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private PropertyHelper() {}

    /**
     * Obtiene el valor de un map dada una key
     *
     * @param obj
     * @param key
     * @return Object
     */
    @SuppressWarnings("unchecked")
    public static Object get(Object obj, String path) {
        String[] keys = path.split("\\.");
        List<String> keysAsList = Arrays.asList(keys);
        Object currentObj = obj;
        for (String key : keysAsList) {
            if (currentObj != null) {
                Map<String, Object> map = (Map<String, Object>) currentObj;
                currentObj = map.get(key);
            }
        }
        return currentObj;
    }

    /**
     * Obtiene el valor de un map dada una key, lo convierte a string que representa
     * una fecha y la convierte a un objeto de tipo LocalDate
     *
     * @param obj
     * @param key
     * @return LocalDate
     */
    public static LocalDate getAsLocalDate(Object obj, String path) {
        String date = getAsString(obj, path);
        if (date != null) {
            return LocalDate.parse(date, SOURCE_DATE_FORMAT);
        }
        return null;
    }

    /**
     * Obtiene el valor de un map dada una key y lo convriete a string
     *
     * @param obj
     * @param key
     * @return String
     */
    public static String getAsString(Object obj, String path) {
        var item = PropertyHelper.get(obj, path);
        return item != null ? item.toString() : null;
    }

    /**
     * Obtiene el valor de un map dada una key y lo convriete a string. Si el valor
     * de la propieda viene null, entonces se regresa una cadena vacía
     *
     * @param obj
     * @param key
     * @return String
     */
    public static String getAsNotNullString(Object obj, String path) {
        var item = PropertyHelper.get(obj, path);
        return item != null ? item.toString() : "";
    }

    /**
     * Obtiene el valor de un map y lo regresa como Boolean
     *
     * @param obj
     * @param key
     * @return Boolean
     */
    public static Boolean getAsBoolean(Object obj, String path) {
        var item = PropertyHelper.get(obj, path);
        return item != null ? Boolean.parseBoolean(item.toString()) : null;
    }

    /**
     * Obtiene el valor de un map y lo regresa como Integer
     *
     * @param obj
     * @param key
     * @return Integer
     */
    public static Integer getAsInteger(Object obj, String path) {
        var item = PropertyHelper.get(obj, path);
        return item != null ? Integer.parseInt(item.toString()) : null;
    }

    /**
     * Obtiene el valor de un map dada una key
     *
     * @param obj
     * @param key
     * @return
     */
    public static List<Object> getAsList(Object obj, String path) {
        return PropertyHelper.resolveList(PropertyHelper.get(obj, path));
    }

    @SuppressWarnings("unchecked")
    public static List<Object> resolveList(Object obj) {
        if (obj == null) {
            return Collections.emptyList();
        }
        if (obj instanceof ArrayList) {
            return (ArrayList<Object>) obj;
        } else {
            return Arrays.asList(obj);
        }
    }

    @SuppressWarnings("unchecked")
    public static Object findFirstOrNull(List<Object> objectList, String prop, String test) {
        return objectList
            .stream()
            .filter(item -> {
                Map<String, Object> map = (Map<String, Object>) item;
                return map.get(prop).equals(test);
            })
            .findFirst()
            .orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static Object findFirstOrNull(List<Object> objectList, String propOne, String testOne, String propTwo, String testTwo) {
        return objectList
            .stream()
            .filter(item -> {
                Map<String, Object> map = (Map<String, Object>) item;
                return map.get(propOne).equals(testOne) && map.get(propTwo).equals(testTwo);
            })
            .findFirst()
            .orElse(null);
    }
}
