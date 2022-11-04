package com.github.liuanxin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.StringJoiner;

@Slf4j
public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        if (log.isInfoEnabled()) {
            String scheme = req.getScheme();
            int port = req.getServerPort();
            boolean http = ("http".equals(scheme) && port != 80);
            boolean https = ("https".equals(scheme) && port != 80 && port != 443);

            StringBuilder domain = new StringBuilder();
            domain.append(scheme).append("://").append(req.getServerName());
            if (http || https) {
                domain.append(":").append(port);
            }
            String url = domain + req.getRequestURI();

            StringBuilder header = new StringBuilder();
            Enumeration<String> headers = req.getHeaderNames();
            while (headers.hasMoreElements()) {
                String headName = headers.nextElement();
                header.append("<").append(headName).append(" : ").append(req.getHeader(headName)).append(">");
            }

            StringJoiner param = new StringJoiner("&");
            Enumeration<String> params = req.getParameterNames();
            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                param.add(paramName + "=" + req.getParameter(paramName));
            }
            log.info("[{} {}] [headers({}) params({})]", req.getMethod(), url, header, param);
        }
        return true;
    }
}
