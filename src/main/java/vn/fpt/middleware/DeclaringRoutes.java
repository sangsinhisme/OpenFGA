package vn.fpt.middleware;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class DeclaringRoutes {

    // neither path nor regex is set - match a path derived from the method name
    @Route(methods = HttpMethod.GET, regex = "/stream", type = Route.HandlerType.NORMAL)
    public void stream(RoutingContext rc) {
        // ...
        rc.next();
    }
}
