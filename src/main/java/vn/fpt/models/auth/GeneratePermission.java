package vn.fpt.models.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratePermission {

    private String username;
    private String baseRole;
    private String resourceId;
    private String resourceName;
    private String app;
}
