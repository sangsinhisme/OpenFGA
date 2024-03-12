package vn.fpt.models.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import java.util.List;

@Getter
@Setter
public class Roles implements Serializable {

    private List<RealmMapping> realmMappings;
    private List<Object> clientMappings;
}
