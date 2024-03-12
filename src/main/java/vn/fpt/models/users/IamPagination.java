package vn.fpt.models.users;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IamPagination<A> {

    private List<A> data;
    private Integer total;
}
