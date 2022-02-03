package com.flycatch.natheer.mock.service.config;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


/**
 * Application configuration properties.
 */
@Validated
@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    @Valid
    private final Util util = new Util();
    private final Cors cors = new Cors();
    @Valid
    private final Natheer natheer = new Natheer();

    /**
     * App util.
     */
    @Getter
    @Setter
    public static class Util {
        private int passwordHashRounds;
        @Positive
        @NotNull
        private Integer streamFetchSize;
    }

    /**
     * Application Cors.
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


    /**
     * Natheer integration.
     */
    @Getter
    @Setter
    public static class Natheer {

        @Valid
        private WatchList watchList = new WatchList();

        /**
         * Natheer watchList.
         */
        @Getter
        @Setter
        public static class WatchList {
            @NotBlank
            private String username;
            @NotBlank
            private String password;
            private String baseUrl;
            @NotBlank
            private String findPersonUrl;
            @NotBlank
            private String addPersonUrl;
            @NotBlank
            private String deletePersonUrl;
            @NotBlank
            private String findPersonsBulkUrl;
            @NotBlank
            private String addPersonsBulkUrl;
            @NotBlank
            private String deletePersonsBulkUrl;
        }
    }
}

