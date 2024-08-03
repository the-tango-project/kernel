package org.apeiron.kernel.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({ AppConfiguration.class, JobrunrConfiguration.class, MailConfiguration.class })
public class ApeironAutoConfiguration {}
