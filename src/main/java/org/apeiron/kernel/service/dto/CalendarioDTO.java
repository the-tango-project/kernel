package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Instant fechaInicio;

    private Instant fechaFinSolicitud;

    private Instant fechaFinRevision;

    private Instant fechaFinReconsideracion;
}
