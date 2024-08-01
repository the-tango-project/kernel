package org.apeiron.kernel.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "kernel", ignoreUnknownFields = false)
@Getter
public class KernelProperties {

    private final ClientApp clientApp = new ClientApp();

    private final Mail mail = new Mail();

    @Setter
    @Getter
    public class ClientApp {

        private String name;
    }

    @Setter
    @Getter
    public class Mail {

        private String host;

        private Integer port;

        private String username;

        private String password;

        private String starttls;

        private String from;

    }
}
