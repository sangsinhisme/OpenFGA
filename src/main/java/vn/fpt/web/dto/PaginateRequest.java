package vn.fpt.web.dto;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginateRequest {

    @QueryParam("first")
    Integer first;

    @QueryParam("max")
    Integer max;

    @QueryParam("app")
    String app;

    @QueryParam("order")
    String order;
}
