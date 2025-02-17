package vn.ftel.perm.web.errors.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import vn.ftel.perm.web.errors.exceptions.EntityConflictException;
import vn.ftel.perm.web.errors.models.ErrorMessage;
import vn.ftel.perm.web.errors.models.ErrorResponse;

import java.util.UUID;

/**
 * Created by Khoa Vu.
 * Mail: khoavu882@gmail.com
 * Date: 2/12/24
 * Time: 9:04 AM
 */
@Slf4j
@Provider
public class EntityConflictExceptionMapper implements ExceptionMapper<EntityConflictException> {

    @Override
    public Response toResponse(EntityConflictException ex) {
        String errorId = UUID.randomUUID().toString();

        log.error(errorId, ex);

        ErrorMessage errorMessage = new ErrorMessage(ex.getEntityName() + "." + ex.getErrorKey(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);

        return Response.status(Response.Status.CONFLICT).entity(errorResponse).build();
    }
}
