package org.identity.identityserver.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author BUI_QUANG_HIEU
 * 5/24/2025
 * TestConfig.java
 */
@Configuration
@RequiredArgsConstructor
public class TestConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public String testBean() {
//        System.out.println(passwordEncoder.encode("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"));
        return "This is a test bean";
    }
}
