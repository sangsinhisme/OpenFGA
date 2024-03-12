package vn.fpt.filters;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.opentracing.Traced;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

@Provider
@Traced
@Slf4j
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        // TODO document why this method is empty
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {
        final var method = containerRequestContext
                .getMethod()
                .toUpperCase(Locale.ROOT);
        final var path = containerRequestContext
                .getUriInfo()
                .getPath();
        final int status = containerResponseContext.getStatus();
        if(!path.contains("/q/metrics")) {
            log.info("(HTTP) method: {}, path: {}, status: {}", method, path, status);
        }
    }

}
