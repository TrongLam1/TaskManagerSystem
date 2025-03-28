package com.taskManagerSystem.TaskManagerSystem.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error.", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed.", HttpStatus.BAD_REQUEST),
    INCORRECT_PASSWORD(1003, "Password is wrong.", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed.", HttpStatus.NOT_FOUND),
    USER_NOT_AVAILABLE(1006, "User is unavailable.", HttpStatus.NOT_ACCEPTABLE),
    UNAUTHENTICATED(1007, "Unauthenticated.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission.", HttpStatus.FORBIDDEN),
    TASK_NOT_EXISTED(1009, "Task not existed.", HttpStatus.NOT_FOUND),
    ROLE_EXISTED(1010, "Role existed.", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(1011, "Role not existed.", HttpStatus.NOT_FOUND),
    PERMISSION_EXISTED(1012, "Permission existed.", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTED(1013, "Permission not existed.", HttpStatus.NOT_FOUND),
    SUB_TASK_NOT_EXISTED(1014, "Sub task not existed.", HttpStatus.NOT_FOUND),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
