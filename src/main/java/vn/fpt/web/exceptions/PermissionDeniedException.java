package vn.fpt.web.exceptions;

public class PermissionDeniedException extends ServiceException {

    public PermissionDeniedException(String entityName, String errorKey, String message) {
        super(entityName, errorKey, message);
    }

    public PermissionDeniedException(String format, String entityName, String errorKey, Object... objects) {
        super(format, entityName, errorKey, objects);
    }

    public PermissionDeniedException(ErrorsEnum error) {
        super(error.getEntityName(), error.getErrorKey(), error.getMessage());
    }
}