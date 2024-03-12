package vn.fpt.models.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateResource {

    private String name;
    private String displayName;
    private List<Scope> scopes;
    private String app;
}
