package vn.fpt.middleware;

import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import vn.fpt.services.client.DmcClientService;

@Slf4j
@ApplicationScoped
public class DeclaringRoutes {

    @RestClient
    DmcClientService dmcClient;

    @Context
    ContainerRequestContext requestContext;

    @Route(regex = "\\/api.*")
    @ActivateRequestContext
    void api(RoutingContext rc) {

        String authentication = rc.request().getHeader(HttpHeaders.AUTHORIZATION);

        if (authentication != null) {
            String token = authentication.replace("Bearer ", "");

            if (token.isBlank()) {
                rc
                        .response()
                        .setStatusCode(Response.Status.UNAUTHORIZED.getStatusCode())
                        .end();
            } else {
                rc.next();
            }
        } else rc
                .response()
                .setStatusCode(Response.Status.UNAUTHORIZED.getStatusCode())
                .end();
    }
}
