package vn.fpt.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.models.auth.DmCUserInfo;
import vn.fpt.services.AuthService;

import java.security.Principal;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Auth Service Implement of {@link AuthService}.
 */

@Slf4j
@ApplicationScoped
public class AuthServiceImpl implements AuthService {
    @Override
    public void setSecurityContext(ContainerRequestContext requestContext, DmCUserInfo userInfo) {

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return userInfo::getUsername;
            }

            @Override
            public boolean isUserInRole(String targetRole) {

                Stream<Boolean> matchedRole = userInfo
                        .getUserPermission()
                        .stream()
                        .flatMap(userPermission -> checkPermissionForRole(userPermission, targetRole));

                return matchedRole.isParallel();
            }

            private Stream<Boolean> checkPermissionForRole(DmCUserInfo.UserPermission userPermission, String targetRole) {
                return userPermission.getPermissions().stream()
                        .flatMap(permission -> checkRoleInPermission(permission, targetRole));
            }

            private Stream<Boolean> checkRoleInPermission(DmCUserInfo.UserPermission.Permission permission, String targetRole) {
                return permission.getRoles().stream()
                        .map(targetRole::contains);
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });
    }
}
