package com.taskManagerSystem.TaskManagerSystem.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String mess) {
        super("Not found " + mess);
    }
}
