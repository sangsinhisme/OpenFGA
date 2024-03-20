package vn.fpt.web.exceptions;

import lombok.Getter;
import vn.fpt.constant.EntitiesConstant;
import vn.fpt.constant.ErrorsKeyConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Getter
public enum ErrorsEnum {
    AUTH_FAILED(EntitiesConstant.AUTH, ErrorsKeyConstant.UNAUTHORIZED, ResourceBundle.getBundle("error-messages").getString("auth.unauthorized")),
    AUTH_NO_ACCESS(EntitiesConstant.AUTH, ErrorsKeyConstant.PERMISSION_DENIED, ResourceBundle.getBundle("error-messages").getString("auth.permission_denied")),


    ;
    // ... add more cases here ...
    private static final Map<String, ErrorsEnum> ENUM_MAP = new HashMap<>();
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

    // Static block to initialize the enum map
    static {
        for (ErrorsEnum errorsEnum : ErrorsEnum.values()) {
            ENUM_MAP.put(errorsEnum.name(), errorsEnum);
        }
    }

    // Static method to get ErrorsEnum instance by name
    public static ErrorsEnum getByName(String name) {
        return ENUM_MAP.get(name);
    }
}