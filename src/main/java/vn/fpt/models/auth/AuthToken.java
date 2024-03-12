package vn.fpt.models.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthToken {

    private Integer code;
    private String message;
    private String token;
}
