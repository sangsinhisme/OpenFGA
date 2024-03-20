package vn.fpt.models.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreatePermission {

    public String description;
    public Role role;
    public List<User> users;
    public Resource resource;
    public List<Scope> scopes;
    public String app;

    @Getter
    @Setter
    public static class Role{
        public String id;
        public String name;
    }

    @Getter
    @Setter
    public static class User{
        public String id;
        public String username;
    }

    @Getter
    @Setter
    public static class Resource{
        public String name;
        public String displayName;
        public String id;
    }

}
