package com.taskManagerSystem.TaskManagerSystem.responses;

import org.springframework.http.HttpStatus;

public class ResponseFailure extends ResponseSuccess {
    public ResponseFailure(HttpStatus status, String message) {
        super(status, message);
    }
}
