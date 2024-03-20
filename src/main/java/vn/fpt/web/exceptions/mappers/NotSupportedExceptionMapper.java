package vn.fpt.web.exceptions.mappers;

import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Provider
public class NotSupportedExceptionMapper implements ExceptionMapper<NotSupportedException> {

    @Override
    public Response toResponse(NotSupportedException ex) {
        String errorId = UUID.randomUUID().toString();

        log.error(errorId, ex);

        return Response
                .status(ex.getResponse().getStatus())
                .build();
    }

}