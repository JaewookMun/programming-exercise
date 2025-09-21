package com.github.jaewookmun.corsresources.config;

import com.github.jaewookmun.corsresources.filter.CredentialedRequestCorsFilter;
import com.github.jaewookmun.corsresources.filter.PreflightedRequestCorsFilter;
import com.github.jaewookmun.corsresources.filter.SimpleRequestCorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SimpleRequestCorsFilter> simpleCorsFilter(SimpleRequestCorsFilter simpleRequestCorsFilter) {
        FilterRegistrationBean<SimpleRequestCorsFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(simpleRequestCorsFilter);
        filterRegistration.addUrlPatterns("/cors/simple-request");

        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<PreflightedRequestCorsFilter> preflightedCorsFilter() {
        FilterRegistrationBean<PreflightedRequestCorsFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new PreflightedRequestCorsFilter());
        filterRegistration.addUrlPatterns("/cors/preflighted-request");

        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<CredentialedRequestCorsFilter> credentialedCorsFilter() {
        FilterRegistrationBean<CredentialedRequestCorsFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new CredentialedRequestCorsFilter());
        filterRegistration.addUrlPatterns("/cors/credentialed-request");

        return filterRegistration;
    }
}
