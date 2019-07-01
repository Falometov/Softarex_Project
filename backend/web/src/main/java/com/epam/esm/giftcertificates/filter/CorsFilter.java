package com.epam.esm.giftcertificates.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for cors problems.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private static final String ALLOW_HEADERS = "Content-Type, Authorization, Accept-Language";
    private static final String HTTP_METHODS = "POST, GET, PUT, OPTIONS, DELETE, PATCH";
    private static final String NUMBER_3600 = "3600";

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, HTTP_METHODS);
        resp.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, NUMBER_3600);
        resp.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALLOW_HEADERS);

        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {

    }
}
