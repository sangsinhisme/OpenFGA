package vn.fpt.models.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RealmMapping implements Serializable {

    private String id;
    private String name;
    private String description;
}
