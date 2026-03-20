package com.ani.pm.config;

import com.ani.pm.service.CartCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {
    @Bean
    public CartCalculator cartCalculator() {
        return new CartCalculator();
    }

    @Bean
    @Profile("dev")
    public String devBanner() {
        return """
                ╔══════════════════════════════════╗
                ║   Running in DEV profile (H2)    ║
                ╚══════════════════════════════════╝
                """;
    }
}
