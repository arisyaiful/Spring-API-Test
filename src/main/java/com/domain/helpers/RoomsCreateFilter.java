package com.domain.helpers;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

public class RoomsCreateFilter implements Filter {
    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean valid = false;
        if(!valid){
            ((HttpServletResponse) response).setStatus(422);
            response.getOutputStream().write("Validation error".getBytes());
            return;
        }
        chain.doFilter(request, response);
    }


}
