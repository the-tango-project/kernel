package org.apeiron.kernel.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonaDTO extends AbstractExtensibleDTO {

    private static final long serialVersionUID = 1L;

    // FIXME: Quitar y utilizar usuarioId u otro
    private String cvu;

    // FIXME: Quitar y utilizar usuarioId u otro
    private String login;

    private String titulo;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String curp;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String rfc;

    private String genero;

    private String correo;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String nivelAcademico;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String gradoAcademico;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String nacionalidad;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String nacionalidadCodigo;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String paisResidencia;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String paisResidenciaCodigo;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String nivelsni;

    // FIXME: QUITAR ESTA PROPIEDAD y dejarla dinámica
    private String rcea;
}
