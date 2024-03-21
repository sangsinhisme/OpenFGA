package vn.fpt.web.errors.mappers;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import vn.fpt.web.errors.models.ErrorMessage;
import vn.fpt.web.errors.models.ErrorResponse;

import java.util.List;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        List<ErrorMessage> errorMessages = ex.getConstraintViolations().stream()
                .map(constraintViolation -> new ErrorMessage(
                        constraintViolation.getPropertyPath().toString(),
                        null,
                        constraintViolation.getMessage()
                ))
                .toList();
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(errorMessages))
                .build();
    }

}
