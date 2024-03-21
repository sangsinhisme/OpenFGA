package vn.fpt.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImaResource {

    @JsonProperty(value = "_id")
    private String id;
    private String name;
    private String displayName;
    private List<BaseObject> scopes;
}
