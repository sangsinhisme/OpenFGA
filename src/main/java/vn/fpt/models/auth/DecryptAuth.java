package vn.fpt.models.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecryptAuth {

    private String encryptedToken;
    private String secretCode;
}
