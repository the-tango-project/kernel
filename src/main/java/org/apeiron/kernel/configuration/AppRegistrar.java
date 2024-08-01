package org.apeiron.kernel.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.context.annotation.ComponentScan;


@EnableConfigurationProperties({ KernelProperties.class })
@EnableReactiveMongoRepositories("org.apeiron.kernel.repository")
@ComponentScan("org.apeiron.kernel")
public class AppRegistrar {

}
