package org.apeiron.kernel.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    /**
     * Rol con los permisos de un administrador del sistema
     */
    public static final String ADMIN = "ROLE_ADMIN";

    /*
     * Rol base que se asigna a todos los usuarios que tienen una cuenta en la
     * plataforma
     */
    public static final String USER = "ROLE_USER";

    /**
     * Rol anónimo que se asigna a un usuario que no ha iniciado sesión en la
     * plataforma o que no cuenta con un usuario y contraseña
     */
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    /**
     * Rol que se asigna a los usuarios que van a atender tareas operativas en la
     * plataforma
     */
    public static final String OPERADOR = "ROLE_OPERADOR";

    /**
     * Rol que se asigna a los usuarios que pueden crear soluciones en la
     * plataforma.
     */
    public static final String INNOVADOR = "ROLE_INNOVADOR";

    /**
     * Rol que se asigna a los usuarios que pueden diseñar y crear pantallas en la
     * plataforma utilizando el administrador de pantallas
     */
    public static final String MODELADOR = "ROLE_MODELADOR";

    /**
     * Rol asignado a las personas que brindan soporte en la plataforma. Este rol
     * tiene vista de operador pero en modo lectura.
     */
    public static final String SOPORTE = "ROLE_SOPORTE";

    private AuthoritiesConstants() {
    }
}
