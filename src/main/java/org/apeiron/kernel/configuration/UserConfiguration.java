package org.apeiron.kernel.configuration;

import org.apeiron.kernel.service.mapper.UserMapper;
import org.apeiron.kernel.service.mapper.DefaultUserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {

    @Bean
    public UserMapper userMapper() {
        return new DefaultUserMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
