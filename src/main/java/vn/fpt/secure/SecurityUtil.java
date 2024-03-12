package vn.fpt.secure;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;
import java.util.Optional;

public final class SecurityUtil {

    static SecurityContext securityContext;

    private SecurityUtil(@Context SecurityContext securityContext) {
        SecurityUtil.securityContext = securityContext;
    }

    public static Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(securityContext)
                .map(SecurityContext::getUserPrincipal)
                .map(Principal::getName);
    }
}
