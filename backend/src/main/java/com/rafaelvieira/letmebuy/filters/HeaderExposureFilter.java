package com.rafaelvieira.letmebuy.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rafae
 */
@Component
public class HeaderExposureFilter implements Filter {
    /**
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.addHeader("access-control-expose-headers", "location");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     *
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
