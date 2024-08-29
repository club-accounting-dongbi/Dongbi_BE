package com.dongbi.projectDongbi.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse <T>{
    private String msg = "";
    private T data = null;

    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(message, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static ApiResponse success() {
        return new ApiResponse<>("", null);
    }
}
