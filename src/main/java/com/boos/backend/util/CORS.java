package com.boos.backend.util;

import jakarta.servlet.*; // Si usas Spring Boot 3.x (o javax.servlet.* si usas Boot 2.x)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORS implements Filter {


    private final List<String> allowedOrigins = Arrays.asList(
            "http://localhost:4200",
            "https://proyecto-boss-frontend-q75lrg46h-nilberavilas-projects.vercel.app",
            "https://proyecto-boss-frontend-1waf24y20-nilberavilas-projects.vercel.app",
            "https://proyecto-boss-frontend-phi-ebon.vercel.app" // In case the main branch deployment gets a simpler url
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // 2. Validamos dinámicamente el Origin de la petición externa
        String origin = request.getHeader("Origin");
        if (origin != null && (origin.endsWith("vercel.app") || origin.startsWith("http://localhost"))) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else if (allowedOrigins.contains(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }

        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");

        // Importante si manejas tokens/sesiones (opcional pero muy recomendado)
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}
