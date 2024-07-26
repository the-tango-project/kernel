package org.apeiron.kernel.service.dto;

import java.io.Serializable;
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
public class NotificacionContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private NotificacionDTO notificacion;
    private SolicitudDTO solicitud;
    private String mailTemplate;
}
