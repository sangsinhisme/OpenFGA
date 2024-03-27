package vn.fpt.security;

import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.models.auth.DmcUserInfo;

import java.util.stream.Stream;

@Slf4j
@RequestScoped
public class SecurityUtil {

    private SecurityUtil() {
    }

    public static boolean isUserHasPermission(String app, DmcUserInfo userInfo) {

        Stream<DmcUserInfo.UserPermission> userPermissions = userInfo
                .getUserPermission()
                .stream();

        return userPermissions
                .anyMatch(userPerm ->
                        userPerm.getName().equals(app) && !userPerm.getPermissions().isEmpty()
                );
    }
}
