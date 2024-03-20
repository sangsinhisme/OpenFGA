package vn.fpt.secure;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.SecurityContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.models.auth.DmCUserInfo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Getter
@Default
public class AppSecurityContext implements SecurityContext {

    @Inject
    DmCUserInfo userInfo;

    public AppSecurityContext(DmCUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<DmCUserInfo.UserPermission.Permission> getPermissions(DmCUserInfo userInfo) {
        List<DmCUserInfo.UserPermission.Permission> permissions = new ArrayList<>();
        if (userInfo != null && userInfo.getUserPermission() != null) {
            for (DmCUserInfo.UserPermission userPermission : userInfo.getUserPermission()) {
                if (userPermission.getPermissions() != null) {
                    permissions.addAll(userPermission.getPermissions());
                }
            }
        }
        return permissions;
    }

    public boolean isOwner(String adminRole) {
        return userInfo
                .getAdminRoles()
                .stream()
                .anyMatch(adminRole::equals);
    }

    @Override
    public Principal getUserPrincipal() {
        return userInfo::getUsername;
    }

    @Override
    public boolean isUserInRole(String targetRole) {

        Stream<Boolean> matchedRole = userInfo
                .getUserPermission()
                .stream()
                .flatMap(userPermission -> checkPermissionInRole(userPermission, targetRole));

        return matchedRole.anyMatch(aBoolean -> aBoolean.equals(true));
    }

    private Stream<Boolean> checkPermissionInRole(DmCUserInfo.UserPermission userPermission, String targetRole) {
        return userPermission.getPermissions().stream()
                .flatMap(permission -> checkRoleInPermission(permission, targetRole));
    }

    private Stream<Boolean> checkRoleInPermission(DmCUserInfo.UserPermission.Permission permission, String targetRole) {
        return permission.getRoles().stream()
                .map(role -> role.contains(targetRole));
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
