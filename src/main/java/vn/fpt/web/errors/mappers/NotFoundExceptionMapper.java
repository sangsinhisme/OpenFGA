package vn.fpt.web.errors.mappers;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.util.UUID;

@Slf4j
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException ex) {
        String errorId = UUID.randomUUID().toString();

        log.error(errorId, ex);

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

}