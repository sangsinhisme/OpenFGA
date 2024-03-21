package vn.fpt.web.errors.exceptions;

import vn.fpt.web.errors.ErrorsEnum;

public class UnauthorizedException extends ServiceException {

    public UnauthorizedException(String entityName, String errorKey, String message) {
        super(entityName, errorKey, message);
    }

    public UnauthorizedException(String format, String entityName, String errorKey, Object... objects) {
        super(format, entityName, errorKey, objects);
    }

    public UnauthorizedException(ErrorsEnum error) {
        super(error.getEntityName(), error.getErrorKey(), error.getMessage());
    }
}