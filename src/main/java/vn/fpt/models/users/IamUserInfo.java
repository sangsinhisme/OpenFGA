package vn.fpt.models.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class IamUserInfo implements Serializable {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private long lastAccess;
    private List<String> groups;
    private Roles roles;
}
