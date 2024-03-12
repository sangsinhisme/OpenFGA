package vn.fpt.models.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPermission {

    private String name;
    private String requestDomain;
    private Attributes attributes;
    private List<Permission> permissions;
}
