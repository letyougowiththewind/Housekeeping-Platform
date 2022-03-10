package com.gnz.pms.configs;

import com.gnz.pms.intercepters.RememberAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rememberMeFilterConfig {


    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RememberAuthenticationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("rememberAuthFilter");
        registration.setOrder(1);
        return registration;
    }
}
