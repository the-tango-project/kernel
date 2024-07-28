package org.apeiron.kernel.service;

import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.jobs.lambdas.JobRunrJob;

/**
 *
 * Intefaz que describe el servicio JobService para administrar {@link JobRunrJob}.
 *
 * Esta implementación se dede utilizar para poder calendarizar tareas que se
 * tengan que ejecutar en un flujo diferente al stream actual y con la finalidad
 * de asegurar la ejecución de la tarea sin importar si existen fallas
 *
 */
public interface JobService {
    /**
     * Ejecuta un metodo en segundo plano
     * @param job Lambda a ejecutar
     * @return Id del Job ejecutado
     */
    JobId schedule(JobLambda job);

    /**
     * Ejecuta un método en segundo plano tipo scheduler-cron.
     *
     * @param cron Expresión de concurrencia
     * @param job el job a ejecutar.
     * @return ID del Job ejecutado.
     */
    String enqueueCron(String cron, JobLambda job);
}
