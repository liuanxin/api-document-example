package com.github.liuanxin.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    /** @see org.springframework.http.HttpHeaders */
    private static final String ORIGIN = "Origin";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    private void handlerCors(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader(ORIGIN);
        String domain = getDomain(request);

        if (origin != null && !origin.equals(domain)) {
            response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            response.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "OPTIONS,HEAD,GET,POST,PUT,DELETE");
            response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS,
                    "Accept, Accept-Encoding, Accept-Language, Cache-Control, " +
                            "Connection, Cookie, DNT, Host, User-Agent, Content-Type, Authorization, " +
                            "X-Requested-With, Origin, Access-Control-Request-headers");
        }
    }
    private String getDomain(HttpServletRequest request) {
        StringBuilder domain = new StringBuilder();

        String scheme = request.getScheme();
        int port = request.getServerPort();
        boolean http = ("http".equals(scheme) && port != 80);
        boolean https = ("https".equals(scheme) && port != 443);

        domain.append(scheme).append("://").append(request.getServerName());
        if (http || https) {
            domain.append(':');
            domain.append(port);
        }
        return domain.toString();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        handlerCors((HttpServletRequest) request, (HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
