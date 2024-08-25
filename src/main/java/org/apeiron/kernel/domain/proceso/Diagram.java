package org.apeiron.kernel.domain.proceso;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagram implements Serializable {

    @Field("x")
    private double x;

    @Field("y")
    private double y;

    @Field("sourceId")
    private String sourceId;

    @Field("targetId")
    private String targetId;

}
