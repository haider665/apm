package com.elk.apm.gateway.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author mojib.haider
 * @since 2/12/25
 */
@Component
public class GatewayFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        // Log request details
        System.out.println("Request URI: " + httpRequest.getRequestURI());
        System.out.println("Remote Address: " + httpRequest.getRemoteAddr());

        // Proceed with the next filter in the chain or the target resource
        filterChain.doFilter(servletRequest, servletResponse);

        // Log after response is sent
        System.out.println("Response sent to client.");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
