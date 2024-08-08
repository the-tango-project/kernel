package org.apeiron.kernel.service.impl;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.firstNonBlank;
import static org.apeiron.kernel.configuration.Constants.Mail.CORREO_DEFAULT;
import static org.apeiron.kernel.configuration.Constants.Mail.MAIL_CONTENT_PLACEHOLDER;
import static org.apeiron.kernel.configuration.Constants.SolicitanteConstants.CORREO_VARIABLE_REGEX;
import static org.apeiron.kernel.configuration.Constants.SolicitanteConstants.DEFAULT_SUBJECT_VARIABLE;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import tech.jhipster.config.JHipsterProperties;

import org.apeiron.kernel.configuration.KernelProperties;
import org.apeiron.kernel.domain.User;
import org.apeiron.kernel.service.NotificacionService;
import org.apeiron.kernel.service.dto.NotificacionContext;
import org.apeiron.kernel.service.dto.PersonaDto;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * Implementación por default para el envio de correos electrónicos
 *
 * {@link NotificacionService}.
 *
 */
@Service
@RequiredArgsConstructor
public class DefaultNotificacionServiceImpl implements NotificacionService {

    private final Logger log = LoggerFactory.getLogger(DefaultNotificacionServiceImpl.class);

    private static final String USER = "user";

    private static final String BASE_URL = "baseUrl";

    private final JHipsterProperties jHipsterProperties;

    private final KernelProperties kernelProperties;

    @Value("${kernel.mail.from}")
    private String mailFrom;

    private final Mailer mailer;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    @Override
    public void send(NotificacionContext context) {
        log.debug("sending message: {}", context.getNotificacion());
        PersonaDto persona = context.getSolicitud().getSolicitante();

        if (isNull(persona.getCorreo())) {
            log.debug("usermail not found for cvu: {}", persona.getCvu());
            return;
        }

        this.mailer.sendMail(
                EmailBuilder
                        .startingBlank()
                        .from(firstNonBlank(context.getNotificacion().getDe(), this.mailFrom, CORREO_DEFAULT))
                        .toMultiple(resolvePara(context))
                        .ccAddresses(resolveCCs(context))
                        .bccAddresses(resolveBccs(context))
                        .withSubject(firstNonBlank(context.getNotificacion().getAsunto(), DEFAULT_SUBJECT_VARIABLE))
                        .withHTMLText(this.resolveMailText(context))
                        .buildEmail(),
                true);
    }

    private String resolveMailText(NotificacionContext context) {
        Context templateContext = new Context();
        templateContext.setVariable("solicitud", context.getSolicitud());
        String template = context.getMailTemplate().replace(MAIL_CONTENT_PLACEHOLDER,
                context.getNotificacion().getMensaje());
        return templateEngine.process(template, templateContext);
    }

    private List<String> resolvePara(NotificacionContext context) {
        String correoSolicitante = context.getSolicitud().getSolicitante().getCorreo();
        return context
                .getNotificacion()
                .getPara()
                .stream()
                .map(correo -> correo.replaceAll(CORREO_VARIABLE_REGEX, correoSolicitante))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene correos para CC de configuración en notificación
     *
     * @param context
     * @return List<String>
     */
    private List<String> resolveCCs(NotificacionContext context) {
        return context.getNotificacion().getCc();
    }

    /**
     * Obtiene correos para Bccs de configuración en notificación
     *
     * @param context
     * @return List<String>
     */
    private List<String> resolveBccs(NotificacionContext context) {
        return context.getNotificacion().getCco();
    }

    @Override
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmail'");
    }

    @Override
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
        Mono.defer(() -> {
            this.sendEmailFromTemplateSync(user, templateName, titleKey);
            return Mono.empty();
        }).subscribe();
    }

    @Override
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        this.sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title");
    }

    @Override
    public void sendCreationEmail(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendCreationEmail'");
    }

    @Override
    public void sendPasswordResetMail(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendPasswordResetMail'");
    }

    private void sendEmailFromTemplateSync(User user, String templateName, String titleKey) {
        if (user.getEmail() == null) {
            log.debug("Email doesn't exist for user '{}'", user.getLogin());
            return;
        }
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        this.sendEmailSync(user.getEmail(), subject, content, false, true);
    }

    private void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug(
                "Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart,
                isHtml,
                to,
                subject,
                content);

        this.mailer.sendMail(
                EmailBuilder
                        .startingBlank()
                        .from(kernelProperties.getMail().getFrom())
                        .to(to)
                        .withSubject(subject)
                        .withHTMLText(content)
                        .buildEmail(),
                true);
    }
}
