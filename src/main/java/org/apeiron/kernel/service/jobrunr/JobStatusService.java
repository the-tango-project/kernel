package org.apeiron.kernel.service.jobrunr;

import java.util.UUID;
import org.apeiron.kernel.service.exception.GeneralException;
import org.apeiron.kernel.service.exception.InvalidArgumentException;
import org.apeiron.kernel.service.exception.NotFoundException;
import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.states.StateName;
import org.jobrunr.storage.JobNotFoundException;
import org.jobrunr.storage.StorageException;
import org.jobrunr.storage.StorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Servicio utilizado para recuperar el status de un Job de Jobrunr.
 */
@Service
public class JobStatusService {

    @Autowired
    private StorageProvider storageProvider;

    /**
     * Método para determinar el estado de un trabajo.
     *
     * @param uuid UUID del trabajo.
     * @return Estado del trabajo
     */
    public StateName jobStatus(@NonNull UUID uuid) {
        try {
            var job = storageProvider.getJobById(new JobId(uuid));
            return job.getJobState().getName();
        } catch (JobNotFoundException ex) {
            throw new NotFoundException(String.format("Trabajo %s no encontrado", uuid), ex);
        } catch (StorageException ex) {
            throw new GeneralException(String.format("Error al consultar el trabajo %s", uuid), ex);
        }
    }

    /**
     * Método para determinar el estado de un trabajo.
     *
     * @param uuid UUID del trabajo.
     * @return Estado del trabajo
     */
    public StateName jobStatus(@NonNull String uuid) {
        try {
            return jobStatus(UUID.fromString(uuid));
        } catch (IllegalArgumentException ex) {
            throw new InvalidArgumentException(String.format("El parámetro %s no es un UUID", uuid), ex);
        }
    }
}
