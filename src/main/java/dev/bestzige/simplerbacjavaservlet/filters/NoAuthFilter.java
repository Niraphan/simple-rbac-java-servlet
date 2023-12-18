package dev.bestzige.simplerbacjavaservlet.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "noAuthFilter", urlPatterns = {"/auth/login", "/auth/register"})
public class NoAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            HttpServletResponse response = ((HttpServletResponse) servletResponse);
            response.sendRedirect(httpRequest.getContextPath() + "/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
