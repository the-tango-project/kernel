package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apeiron.kernel.domain.enumeration.EstadoForm;
import org.apeiron.kernel.domain.enumeration.MenuElement;
import org.apeiron.kernel.domain.enumeration.TipoComponente;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Form.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Document(collection = "forms")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Form extends AbstractAuditingEntity<String> implements AbstractExtensible {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("estado")
    private EstadoForm estado;

    @Field("title")
    private String title;

    @Field("menuName")
    private MenuElement menuName;

    @Field("description")
    private String description;

    @Field("tipo")
    private TipoComponente tipo;

    @Field("icon")
    private String icon;

    @Field("tags")
    private List<String> tags = new ArrayList<>();

    @Field("html")
    private String html;

    @Field("vuejs")
    private String vuejs;

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();
}
