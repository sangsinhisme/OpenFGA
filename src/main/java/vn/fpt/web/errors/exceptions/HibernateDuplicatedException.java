package vn.fpt.web.errors.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.hibernate.exception.ConstraintViolationException;
import vn.fpt.web.errors.models.ErrorMessage;
import vn.fpt.web.errors.models.ErrorResponse;

import java.util.UUID;

@Provider
public class HibernateDuplicatedException implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        String errorId = UUID.randomUUID().toString();
        ErrorMessage errorMessage = new ErrorMessage(ex.getSQLException().getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
        return Response
                .status(Response.Status.CONFLICT)
                .entity(errorResponse)
                .build();
    }

}