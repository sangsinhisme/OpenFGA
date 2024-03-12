package vn.fpt.web.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorId;
    private List<ErrorMessage> errors;

    public ErrorResponse(String errorId, ErrorMessage errorMessage) {
        this.errorId = errorId;
        this.errors = List.of(errorMessage);
    }

    public ErrorResponse(ErrorMessage errorMessage) {
        this(null, errorMessage);
    }

    public ErrorResponse(List<ErrorMessage> errors) {
        this.errorId = null;
        this.errors = errors;
    }

    public ErrorResponse() {
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    public static class ErrorMessage {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String path;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String errorKey;
        private String message;

        public ErrorMessage(String path, String errorKey, String message) {
            this.path = path;
            this.errorKey = errorKey;
            this.message = message;
        }

        public ErrorMessage(String message) {
            this.path = null;
            this.errorKey = null;
            this.message = message;
        }

        public ErrorMessage(String errorKey, String message) {
            this.path = null;
            this.errorKey = errorKey;
            this.message = message;
        }

        public ErrorMessage() {
        }
    }

}