package org.apeiron.kernel.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String de;

    private List<String> para = new ArrayList<>();

    private List<String> cc = new ArrayList<>();

    private List<String> cco = new ArrayList<>();

    private String asunto;

    private String mensaje;

    private boolean activada;

    private boolean enviada;

    private Instant fechaDeEnvio;
}
