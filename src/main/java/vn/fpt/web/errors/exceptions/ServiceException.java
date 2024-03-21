package vn.fpt.web.errors.exceptions;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import lombok.Getter;
import lombok.ToString;
import vn.fpt.web.errors.ErrorsEnum;

@Getter
@ToString
@RequestScoped
public class ServiceException extends RuntimeException {

    private final String entityName;

    private final String errorKey;

    @Context
    ContainerRequestContext requestContext;

    public ServiceException(String entityName, String errorKey, String message) {
        super(message);
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public ServiceException(String format, String entityName, String errorKey, Object... objects) {
        super(String.format(format, objects));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public ServiceException(ErrorsEnum error) {
        super(error.getMessage());
        this.entityName = error.getEntityName();
        this.errorKey = error.getErrorKey();
    }
}