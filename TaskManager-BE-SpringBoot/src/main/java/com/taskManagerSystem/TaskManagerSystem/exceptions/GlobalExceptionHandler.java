package com.taskManagerSystem.TaskManagerSystem.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception: ", exception);
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        errorResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ErrorResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(errorCode.getCode());
        errorResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(errorCode.getCode());
        errorResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri", ""));
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        String message = e.getMessage();
        int start = message.lastIndexOf("[");
        int end = message.lastIndexOf("]");
        message = message.substring(start + 1, end - 1);
        errorResponse.setMessage(message);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
