package vn.fpt.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DmcUserInfo {

    private int code;
    private String messages;
    private String username;
    private String email;
    private String fullName;
    private boolean userEnabled;
    private String deptHead;
    private String lineManager;
    private String company;
    private String location;
    @JsonProperty(value = "access_token")
    private String accessToken;
    private String exp;
    @JsonProperty(value = "origin-request")
    private String originRequest;
    private List<UserPermission> userPermission;
    @JsonProperty(value = "admin_roles")
    private List<String> adminRoles;
    private List<String> requestApps;
    private String realmName;

    @Getter
    @Setter
    @ToString
    public static class UserPermission {

        private String name;
        private String requestDomain;
        private Attributes attributes;
        private List<Permission> permissions;

        @Getter
        @Setter
        @ToString
        public static class Attributes {

            private List<String> baseUrl;
            private List<String> displayName;
            private List<String> domain;
            private List<String> imgPath;
            private List<String> description;
            private List<String> requestAccess;
            private List<String> version;
        }


        @Getter
        @Setter
        @ToString
        public static class Permission {
            private String resource;
            private List<String> roles;
            private List<String> action;
        }
    }
}
