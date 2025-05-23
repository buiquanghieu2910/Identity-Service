package org.identity.identityserver.configuration;

import org.identity.identityserver.component.DatabaseClientRegistrationRepository;
import org.identity.identityserver.filter.DynamicCorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.filter.ForwardedHeaderFilter;

/**
 * @author BUI_QUANG_HIEU
 * 5/17/2025
 * ApplicationConfig.java
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public FilterRegistrationBean<DynamicCorsFilter> corsFilterRegistration(DynamicCorsFilter corsFilter) {
        FilterRegistrationBean<DynamicCorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(corsFilter);
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

    @Bean
    @Primary
    public ClientRegistrationRepository clientRegistrationRepository(DatabaseClientRegistrationRepository repo) {
        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
