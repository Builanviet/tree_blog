package com.example.treeblog.dto.auth;

public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private Object error;

    public ApiResponse(int status, String message, T data, Object error) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data, null);
    }

    public static <T> ApiResponse<T> error(int status, String message, Object errorDetails) {
        return new ApiResponse<>(status, message, null, errorDetails);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
