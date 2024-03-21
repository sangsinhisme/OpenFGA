package vn.fpt.web.errors.exceptions;

import vn.fpt.web.errors.ErrorsEnum;

public class NotAcceptableException extends ServiceException {

    public NotAcceptableException(String entityName, String errorKey, String message) {
        super(entityName, errorKey, message);
    }

    public NotAcceptableException(String format, String entityName, String errorKey, Object... objects) {
        super(format, entityName, errorKey, objects);
    }

    public NotAcceptableException(ErrorsEnum error) {
        super(error.getEntityName(), error.getErrorKey(), error.getMessage());
    }
}