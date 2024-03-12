package vn.fpt.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DmCUserInfo {

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
}
