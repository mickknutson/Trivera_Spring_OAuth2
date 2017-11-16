package com.springclass.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Spring Security Config Class
 * @see {@link ResourceServerConfigurerAdapter}
 * @since chapter16.00
 */
@Configuration

// LAB: Enable the Resource Server:

public class OAuth2ResourceServerConfig
        extends ResourceServerConfigurerAdapter {

	// OPTIONAL in order to allow Cross-Origin Resource Sharing
    @Override
    public void configure(final HttpSecurity http) throws Exception {

        // Allow AJAX preflight requests via HttpMethod.OPTIONS to be made without
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    }

} // The End...
