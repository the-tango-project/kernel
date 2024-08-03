package org.apeiron.kernel.configuration;

import org.apeiron.kernel.KernelApplication;
import org.apeiron.kernel.repository.UserRepository;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultEntityRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AutoConfigurationPackages.register(registry, KernelApplication.class.getPackageName());
        AutoConfigurationPackages.register(registry, UserRepository.class.getPackageName());
    }
}