package org.apeiron.kernel.service.impl;

import java.time.ZoneId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apeiron.kernel.service.JobService;
import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.jobs.lambdas.JobRunrJob;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Service;

/**
 *
 * Implementación del servicio JobService para administrar {@link JobRunrJob}
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobScheduler jobScheduler;

    public JobId schedule(JobLambda job) {
        var id = jobScheduler.enqueue(job);
        log.debug("Se creo el trabajo {}", id);
        return id;
    }

    @Override
    public String enqueueCron(String cron, JobLambda job) {
        var id = jobScheduler.scheduleRecurrently(UUID.randomUUID().toString(), cron, ZoneId.systemDefault(), job);
        log.debug("Se creó el job cron, con ID: {}", id);
        return id;
    }
}
