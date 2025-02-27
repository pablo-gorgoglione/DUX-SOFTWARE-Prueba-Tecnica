package com.base_api.model.common;

import com.base_api.types.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO<T> {
    private static final String SUCCESS = "success";
    boolean success;
    @NonNull
    private String message;
    private T content;
    private Enum errorCode;

    private ResponseDTO(String message, T content) {
        this.errorCode = null;
        this.success = true;
        this.message = message;
        this.content = content;
    }

    private ResponseDTO(String message, Enum errorCode) {
        this(message, errorCode, null, false);
    }

    private ResponseDTO(String message, Enum errorCode, T content, boolean success) {
        this.message = message;
        this.errorCode = errorCode;
        this.content = content;
        this.success = success;
    }

    public static ResponseDTO<String> ofSuccess() {
        return new ResponseDTO<>(SUCCESS, "");
    }

    public static ResponseDTO<String> ofSuccess(String message) {
        return new ResponseDTO<>(SUCCESS, message);
    }

    public static <T> ResponseDTO<T> ofError(String message) {
        return new ResponseDTO<>(message, ErrorCode.GENERIC_ERROR);
    }

}
