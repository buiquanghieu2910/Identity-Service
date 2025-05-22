package org.identity.identityserver.configuration;

import org.identity.identityserver.filter.DynamicCorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
}
