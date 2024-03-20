package vn.fpt.web.exceptions.mappers;

import jakarta.transaction.RollbackException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.web.exceptions.ErrorResponse;

import java.util.UUID;

@Slf4j
@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {

    @Override
    public Response toResponse(RollbackException ex) {
        String errorId = UUID.randomUUID().toString();

        log.error(errorId, ex);

        ErrorResponse.ErrorMessage errorMessage = new ErrorResponse.ErrorMessage(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }

}