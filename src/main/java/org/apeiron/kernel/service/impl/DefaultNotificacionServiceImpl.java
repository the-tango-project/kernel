package org.apeiron.kernel.service.impl;

import static java.util.Objects.isNull;
import static org.apeiron.kernel.config.Constants.Mail.CORREO_DEFAULT;
import static org.apeiron.kernel.config.Constants.Mail.MAIL_CONTENT_PLACEHOLDER;
import static org.apeiron.kernel.config.Constants.SolicitanteConstants.CORREO_VARIABLE_REGEX;
import static org.apeiron.kernel.config.Constants.SolicitanteConstants.DEFAULT_SUBJECT_VARIABLE;
import static org.apache.commons.lang3.StringUtils.firstNonBlank;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apeiron.kernel.service.NotificacionService;
import org.apeiron.kernel.service.dto.NotificacionContext;
import org.apeiron.kernel.service.dto.PersonaDTO;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

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

    @Value("${jhipster.mail.from}")
    private String mailFrom;

    private final Mailer mailer;

    private final SpringTemplateEngine templateEngine;

    @Override
    public void send(NotificacionContext context) {
        log.debug("sending message: {}", context.getNotificacion());
        PersonaDTO persona = context.getSolicitud().getSolicitante();

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
                true
            );
    }

    private String resolveMailText(NotificacionContext context) {
        Context templateContext = new Context();
        templateContext.setVariable("solicitud", context.getSolicitud());
        String template = context.getMailTemplate().replace(MAIL_CONTENT_PLACEHOLDER, context.getNotificacion().getMensaje());
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
}
