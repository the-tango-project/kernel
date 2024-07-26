package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("de")
    private String de;

    @Field("para")
    private List<String> para = new ArrayList<>();

    @Field("cc")
    private List<String> cc = new ArrayList<>();

    @Field("cco")
    private List<String> cco = new ArrayList<>();

    @Field("asunto")
    private String asunto;

    @Field("mensaje")
    private String mensaje;

    @Field("activada")
    private boolean activada;

    @Field("enviada")
    private boolean enviada;

    @Field("fechaDeEnvio")
    private Instant fechaDeEnvio;
}
