package org.apeiron.kernel.configuration;

import org.simplejavamail.api.mailer.CustomMailer;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.OperationalConfig;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.mail.Session;
import org.simplejavamail.api.email.Email;

import jakarta.mail.internet.MimeMessage;
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
        log.debug("Usando Mailer LOG");
        return MailerBuilder
                // Información 'dummy' solo para utilizar un 'custom mailer'.
                .withSMTPServer("localhost", 0).withCustomMailer(new CustomMailer() {
                    @Override
                    public void testConnection(OperationalConfig operationalConfig, Session session) {
                        // No necesario
                    }

                    @Override
                    public void sendMessage(OperationalConfig operationalConfig, Session session, Email email,
                            MimeMessage message) {
                        log.debug("Simulando envío de correo electrónico: {}", email);
                    }
                }).buildMailer();
    }

    @Bean
    @Profile(JHipsterConstants.SPRING_PROFILE_PRODUCTION)
    public Mailer getDefaultJavaMailSender() {

        return MailerBuilder
                .withSMTPServerHost(mailServerHost)
                .withSMTPServerPort(mailServerPort)
                .withSMTPServerUsername(mailServerUsername)
                .withSMTPServerPassword(mailServerPassword)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();
    }

}
