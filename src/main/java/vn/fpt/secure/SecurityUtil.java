package vn.fpt.secure;

import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.models.auth.DmCUserInfo;

import java.util.stream.Stream;

@Slf4j
@RequestScoped
public class SecurityUtil {

    private SecurityUtil() {
    }

    public static boolean isUserHasPermission(String app, DmCUserInfo userInfo) {

        Stream<DmCUserInfo.UserPermission> userPermissions = userInfo
                .getUserPermission()
                .stream();

        return userPermissions
                .anyMatch(userPerm ->
                        userPerm.getName().equals(app) && !userPerm.getPermissions().isEmpty()
                );
    }
}
