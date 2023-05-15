package com.domain.helpers;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class APIKeyValidatorFilter implements Filter {

    private static final String KeyValidator = "Basic 797adas79-43asdf6-6575ads7-asdasn99";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String key = req.getHeader("X-API-KEY");
        if(key == null) {
            ((HttpServletResponse) response).setStatus(401);
            response.getOutputStream().write("API Key is missing!".getBytes());
            return;
        }

        if(!KeyValidator.equals(key)) {
            ((HttpServletResponse) response).setStatus(403);
            response.getOutputStream().write("API Key is invalid".getBytes());
            return;
        }

        chain.doFilter(request, response);
    }
}
