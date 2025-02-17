package vn.ftel.perm.web.errors.mappers;

import vn.ftel.perm.constant.AppConstant;
import vn.ftel.perm.constant.EntitiesConstant;
import vn.ftel.perm.constant.ErrorsKeyConstant;
import vn.ftel.perm.utils.ResourceBundleUtil;
import vn.ftel.perm.web.errors.models.ErrorMessage;
import vn.ftel.perm.web.errors.models.ErrorResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.util.MissingResourceException;
import java.util.UUID;

/**
 * Created by Khoa Vu.
 * Mail: khoavu882@gmail.com
 * Date: 2/12/24
 * Time: 9:04 AM
 */
@Slf4j
@Provider
@RequestScoped
public class MissingResourceExceptionMapper implements ExceptionMapper<MissingResourceException> {

    @Context
    ContainerRequestContext requestContext;

    @Override
    public Response toResponse(MissingResourceException ex) {
        String errorId = UUID.randomUUID().toString();

        log.error(errorId, ex);

        String errorKey = EntitiesConstant.SYSTEM + "." + ErrorsKeyConstant.BUNDLE_DOES_NOT_EXIST;
        String message = ResourceBundleUtil.getKeyWithResourceBundle(
                AppConstant.I18N_ERROR, requestContext.getLanguage(), errorKey);

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorKey(errorKey);
        errorMessage.setMessage(message);

        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}
