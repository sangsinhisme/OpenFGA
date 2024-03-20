package vn.fpt.web.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ServiceException extends RuntimeException {

    private final String entityName;

    private final String errorKey;

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

    public ServiceException(ErrorsEnum error, String entityName, String errorKey) {
        super(error.getMessage());
        this.entityName = entityName;
        this.errorKey = errorKey;
    }
}