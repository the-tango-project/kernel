package org.apeiron.kernel.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SolucionConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("max_number_of_solicitudes_per_user")
    private Integer maxNumberOfSolicitudesPerUser;
}
