package vn.fpt.web.exceptions;

import lombok.Getter;
import vn.fpt.constant.EntitiesConstant;
import vn.fpt.constant.ErrorsKeyConstant;

import java.util.ResourceBundle;

@Getter
public enum ErrorsEnum {
    AUTH_FAILED(EntitiesConstant.AUTH, ErrorsKeyConstant.UNAUTHORIZED, ResourceBundle.getBundle("error-messages").getString("auth.unauthorized")),

    ;
    // ... add more cases here ...
    private final String entityName;
    private final String errorKey;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorsEnum(String entityName, String errorKey, String message) {
        this.entityName = entityName;
        this.errorKey = errorKey;
        this.message = message;
    }
}