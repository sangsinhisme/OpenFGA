package vn.fpt.web.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.web.exceptions.ErrorResponse;
import vn.fpt.web.exceptions.PermissionDeniedException;
import vn.fpt.web.exceptions.ServiceException;
import vn.fpt.web.exceptions.UnauthorizedException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException ex) {
        String errorId = UUID.randomUUID().toString();

        log.error(errorId, ex);

        ErrorResponse.ErrorMessage errorMessage = new ErrorResponse.ErrorMessage(ex.getEntityName() + "." + ex.getErrorKey(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);

        if(ex.getClass().equals(UnauthorizedException.class)) {

            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();

        } else if(ex.getClass().equals(PermissionDeniedException.class)) {

            return Response
                    .status(Response.Status.FORBIDDEN)
                    .build();
        } else {

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
        }
    }

}