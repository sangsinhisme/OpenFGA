package vn.ftel.perm.web.dto;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InviteUserInput {

    @HeaderParam(HttpHeaders.AUTHORIZATION)
    String token;

    @QueryParam("app")
    String app;

    @QueryParam("email")
    String email;
}
