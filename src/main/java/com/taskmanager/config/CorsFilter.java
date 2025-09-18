package com.taskmanager.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        
        // Allow all origins
        headers.add("Access-Control-Allow-Origin", "*");
        
        // Allow specific headers
        headers.add("Access-Control-Allow-Headers", "*");
        
        // Allow all HTTP methods
        headers.add("Access-Control-Allow-Methods", "*");
        
        // Allow credentials
        headers.add("Access-Control-Allow-Credentials", "true");
        
        // Cache preflight request for 1 day
        headers.add("Access-Control-Max-Age", "86400");
        
        // Expose headers to the browser
        headers.add("Access-Control-Expose-Headers", "*");
    }
}
