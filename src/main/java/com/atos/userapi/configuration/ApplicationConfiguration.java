package com.atos.userapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfiguration {

    @Bean
    public TimeZone timeZone(){
        TimeZone defaultTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(defaultTimeZone);
        return defaultTimeZone;
    }
}
