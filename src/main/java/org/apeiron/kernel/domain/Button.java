package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
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
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Button {

    @Field("icon")
    private String icon;

    @Field("nombre")
    private String nombre;

    @Field("destino")
    private String destino;

    @Field("tooltip")
    private String tooltip;

    @Field("roles")
    private List<RolAutoridad> roles = new ArrayList<>();

    @Field("expresion")
    private String expresion;

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
