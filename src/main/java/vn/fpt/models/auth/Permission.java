package vn.fpt.models.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Permission {
    private String resource;
    private List<String> roles;
    private List<String> action;
}
