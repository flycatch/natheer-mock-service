package com.flycatch.natheer.mock.service.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Cors cors = new Cors();

    /**
     * Cors static class .
     */
    @Getter
    @Setter
    public static class Cors {
        private long maxAgeInSec = 1800;
        private boolean allowCredentials;
        private String pathPattern = "/**";
        private List<String> allowedOrigins = new ArrayList<>();
        private List<String> allowedMethods = new ArrayList<>();
        private List<String> allowedHeaders = new ArrayList<>();
        private List<String> exposedHeaders = new ArrayList<>();
    }
}
