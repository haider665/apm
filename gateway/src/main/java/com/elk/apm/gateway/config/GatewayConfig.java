package com.elk.apm.gateway.config;

import com.elk.apm.gateway.filter.GatewayFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mojib.haider
 * @since 2/12/25
 */
@Configuration
public class GatewayConfig {

    @Bean
    public FilterRegistrationBean<GatewayFilter> loggingFilter() {
        FilterRegistrationBean<GatewayFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new GatewayFilter());
        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(2);

        return registrationBean;
    }
}
