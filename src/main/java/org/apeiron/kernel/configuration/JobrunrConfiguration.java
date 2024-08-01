package org.apeiron.kernel.configuration;

import com.mongodb.client.MongoClient;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import tech.jhipster.config.JHipsterConstants;

@Configuration
public class JobrunrConfiguration {

    @Bean
    @Profile("!" + JHipsterConstants.SPRING_PROFILE_PRODUCTION)
    public StorageProvider memoryStorageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }

    @Bean
    @Profile(JHipsterConstants.SPRING_PROFILE_PRODUCTION)
    public StorageProvider databaseStorageProvider(JobMapper jobMapper, MongoClient mongoClient, MongoTemplate mongoTemplate) {
        MongoDBStorageProvider storageProvider = new MongoDBStorageProvider(mongoClient, mongoTemplate.getDb().getName(), "jrun_");
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }
}
