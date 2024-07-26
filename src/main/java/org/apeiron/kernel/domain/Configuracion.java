package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.RolAutoridad;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("carpeta")
    private String carpeta;

    @Field("hasMoreDocuments")
    private Boolean hasMoreDocuments;

    @Field("maxNumberOfDocuments")
    private Integer maxNumberOfDocuments;

    @Field("maxFileSize")
    private Integer maxFileSize;

    @Field("mediaTypes")
    private List<String> mediaTypes = new ArrayList<>();

    @Field("documentos")
    private List<DocumentoConfiguracion> documentos = new ArrayList<>();

    @Field("reglas")
    private List<Rule> reglas = new ArrayList<>();

    @Field("roles")
    private List<RolAutoridad> roles = new ArrayList<>();
}
