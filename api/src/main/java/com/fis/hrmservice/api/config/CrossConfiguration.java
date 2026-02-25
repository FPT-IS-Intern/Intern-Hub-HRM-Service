package com.fis.hrmservice.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrossConfiguration {

    @Bean
    public CorsFilter CrossConfiguration() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");   // cho phép tất cả domain
        config.addAllowedHeader("*");          // cho phép tất cả header
        config.addAllowedMethod("*");          // cho phép tất cả method (GET, POST, PUT, DELETE,...)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
