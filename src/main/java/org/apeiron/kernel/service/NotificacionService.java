package org.apeiron.kernel.service;

import org.apeiron.kernel.service.dto.NotificacionContext;

/**
 * NotificacionService, se utiliza en el proceso de notificaciones asincronas de
 * la plataforma
 */
public interface NotificacionService {
    public void send(NotificacionContext context);
}
