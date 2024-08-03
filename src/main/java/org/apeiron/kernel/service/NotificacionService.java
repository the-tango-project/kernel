package org.apeiron.kernel.service;

import org.apeiron.kernel.domain.User;
import org.apeiron.kernel.service.dto.NotificacionContext;

/**
 * NotificacionService, se utiliza en el proceso de notificaciones asincronas de
 * la plataforma
 */
public interface NotificacionService {
    public void send(NotificacionContext context);

    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    public void sendEmailFromTemplate(User user, String templateName, String titleKey);

    public void sendActivationEmail(User user);

    public void sendCreationEmail(User user);

    public void sendPasswordResetMail(User user);
}
