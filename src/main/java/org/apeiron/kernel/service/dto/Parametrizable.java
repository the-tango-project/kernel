package org.apeiron.kernel.service.dto;

/**
 * Interfaz Parametrizable que define los principales métodos que hacen a un
 * objeto Parametrizable en Áperion. Un objecto Parametrizable debe definir un
 * conjunto de Argumentos que son incluidos en el contexto de ejecición de
 * validación y acciones en el Ápeiron
 *
 * Los principales objectos que son parametrizables en el áperion son:
 *
 * {@link RuleDto}
 * {@link ActionDto}
 *
 */
public interface Parametrizable {
    /**
     * Método get para la propiedad clave de un parámetro. Cada parámetro debe de
     * ser único y no se puede repetir, por lo que este método debe ser implementado
     * de tal maneara que el objecto que lo recupere siempre de un valor único para
     * un parámetro
     *
     * @return String clave
     *
     */
    String getClave();

    /**
     * Método get para los objectos parametrizables y que regresan un arreglo de
     * Objectos
     * {@link org.apeiron.kernel.service.dto.ArgumentDto}
     *
     * @return ArgumentDto[] arreglo de objetos
     *         {@link org.apeiron.kernel.service.dto.ArgumentDto}
     */
    ArgumentDto[] getArguments();
}
