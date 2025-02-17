package vn.ftel.perm.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TupleKeyEntity implements Serializable {
    private String user;
    private String relation;
    private String object;

}
