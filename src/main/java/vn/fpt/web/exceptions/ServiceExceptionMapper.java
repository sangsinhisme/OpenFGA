package vn.fpt.web.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.UUID;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException ex) {
        String errorId = UUID.randomUUID().toString();
        ErrorResponse.ErrorMessage errorMessage = new ErrorResponse.ErrorMessage(ex.getEntityName() + "." + ex.getErrorKey(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);

        if(ex.getClass().equals(UnauthorizedException.class)) {
            errorMessage.setMessage(ex.getMessage());
            errorResponse.setErrorId(errorId);
            errorResponse.setErrors(List.of(errorMessage));

            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(errorResponse)
                    .build();

        } else if(ex.getClass().equals(PermissionDeniedException.class)) {
            errorMessage.setMessage(ex.getMessage());
            errorResponse.setErrorId(errorId);
            errorResponse.setErrors(List.of(errorMessage));

            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(errorResponse)
                    .build();
        } else {

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
        }
    }

}