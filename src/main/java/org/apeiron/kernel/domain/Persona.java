package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Información general de una persona
 *
 * @version 1.0
 * @since 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements AbstractExtensible, Serializable {

    private static final long serialVersionUID = 1L;

    //FIXME: QUITAR ESTA PROPIEDAD
    @Field("cvu")
    private String cvu;

    @Field("login")
    private String login;

    @Field("titulo")
    private String titulo;

    @Field("nombre")
    private String nombre;

    @Field("apellidoPaterno")
    private String apellidoPaterno;

    @Field("apellidoMaterno")
    private String apellidoMaterno;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("curp")
    private String curp;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("rfc")
    private String rfc;

    @Field("genero")
    private String genero;

    @Field("correo")
    private String correo;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("nivelAcademico")
    private String nivelAcademico;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("gradoAcademico")
    private String gradoAcademico;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("nacionalidad")
    private String nacionalidad;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("nacionalidadCodigo")
    private String nacionalidadCodigo;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("paisResidencia")
    private String paisResidencia;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("paisResidenciaCodigo")
    private String paisResidenciaCodigo;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("nivelsni")
    private String nivelsni;

    //FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    @Field("rcea")
    private String rcea;

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
