package vn.fpt.middleware;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import vn.fpt.models.audit.AuditListener;
import vn.fpt.models.auth.DmcUserInfo;
import vn.fpt.security.AppSecurityContext;
import vn.fpt.security.SecurityUtil;
import vn.fpt.services.client.DmcClientService;
import vn.fpt.web.errors.ErrorsEnum;
import vn.fpt.web.errors.exceptions.PermissionDeniedException;
import vn.fpt.web.errors.exceptions.UnauthorizedException;

import java.util.Locale;

@Slf4j
@Traced
@Provider
@ApplicationScoped
public class HttpFilters implements ContainerRequestFilter, ContainerResponseFilter {

    @RestClient
    DmcClientService dmcClient;

    @Override
    public void filter(ContainerRequestContext requestContext) throws PermissionDeniedException, UnauthorizedException {

        String authentication = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String language = requestContext.getHeaderString(HttpHeaders.CONTENT_LANGUAGE);

        if(language == null) {
            Locale locale = Locale.getDefault();
            requestContext.getHeaders().putSingle(HttpHeaders.CONTENT_LANGUAGE, locale.getLanguage());
        }

        if (authentication != null) {

            String token = authentication.replace("Bearer ", "");

            if (!token.isBlank()) {
                try {
                    DmcUserInfo userInfo = dmcClient.getUserPermission("churn", "cads", token);

                    if (SecurityUtil.isUserHasPermission("churn", userInfo)) {
                        requestContext.setSecurityContext(new AppSecurityContext(userInfo));
                        AuditListener.setCurrentUser(userInfo.getUsername());
                    } else throw new PermissionDeniedException(ErrorsEnum.AUTH_NO_ACCESS);

                } catch (WebApplicationException ex) {

                    log.error(ex.getMessage());
                    throw new UnauthorizedException(ErrorsEnum.AUTH_FAILED);
                }
            }
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        final var method = requestContext
                .getMethod()
                .toUpperCase(Locale.ROOT);
        final var path = requestContext
                .getUriInfo()
                .getPath();
        final int status = responseContext.getStatus();

        if (!path.contains("/q/metrics")) {
            log.info("(HTTP) method: {}, path: {}, status: {}, username: {}, locate: {}", method, path, status, AuditListener.getCurrentUser(), requestContext.getLanguage());
        }
        AuditListener.clearCurrentUser();

    }
}
