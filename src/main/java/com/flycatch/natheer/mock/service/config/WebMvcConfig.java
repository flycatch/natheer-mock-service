package com.flycatch.natheer.mock.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig  class .
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AppProperties properties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        final AppProperties.Cors cors = properties.getCors();
        registry
            .addMapping(cors.getPathPattern())
            .allowedOrigins(cors.getAllowedOrigins().toArray(new String[0]))
            .allowedMethods(cors.getAllowedHeaders().toArray(new String[0]))
            .allowedHeaders(cors.getAllowedHeaders().toArray(new String[0]))
            .exposedHeaders(cors.getExposedHeaders().toArray(new String[0]))
            .allowCredentials(cors.isAllowCredentials())
            .maxAge(cors.getMaxAgeInSec());
    }
}

