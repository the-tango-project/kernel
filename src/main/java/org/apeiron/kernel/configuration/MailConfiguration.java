package org.apeiron.kernel.configuration;

import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import lombok.extern.slf4j.Slf4j;
import tech.jhipster.config.JHipsterConstants;

@Slf4j
@Configuration
public class MailConfiguration {

    @Value("${kernel.mail.host}")
    private String mailServerHost;

    @Value("${kernel.mail.port}")
    private Integer mailServerPort;

    @Value("${kernel.mail.username}")
    private String mailServerUsername;

    @Value("${kernel.mail.password}")
    private String mailServerPassword;

    @Value("${kernel.mail.starttls}")
    private String mailServerStartTls;

    @Bean
    @Profile("!" + JHipsterConstants.SPRING_PROFILE_PRODUCTION)
    public Mailer mailerToLog() {
        // Ensure logging emails insted of sending
        // https://www.simplejavamail.org/debugging.html#section-debug-mode
        log.debug("Configuration for ensure Logging emails instead of sending");
        return MailerBuilder.withTransportModeLoggingOnly().buildMailer();
    }

    @Bean
    @Profile(JHipsterConstants.SPRING_PROFILE_PRODUCTION)
    public Mailer getDefaultJavaMailSender() {
        log.debug("Using Productio Mail credentials");
        return MailerBuilder
                .withSMTPServerHost(mailServerHost)
                .withSMTPServerPort(mailServerPort)
                .withSMTPServerUsername(mailServerUsername)
                .withSMTPServerPassword(mailServerPassword)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();
    }

}
