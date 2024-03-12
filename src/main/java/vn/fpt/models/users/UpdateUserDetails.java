package vn.fpt.models.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateUserDetails implements Serializable {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
