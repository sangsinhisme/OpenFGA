package vn.fpt.web.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.net.URISyntaxException;
import java.util.UUID;

@Provider
public class URISyntaxExceptionMapper implements ExceptionMapper<URISyntaxException> {

    @Override
    public Response toResponse(URISyntaxException e) {
        String errorId = UUID.randomUUID().toString();
        ErrorResponse.ErrorMessage errorMessage = new ErrorResponse.ErrorMessage(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }

}