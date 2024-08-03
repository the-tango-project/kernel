package org.apeiron.kernel.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties({ KernelProperties.class })
@ComponentScan("org.apeiron.kernel")
@EnableReactiveMongoRepositories("org.apeiron.kernel.repository")
@Import(UserConfiguration.class)
public class AppConfiguration {

}
