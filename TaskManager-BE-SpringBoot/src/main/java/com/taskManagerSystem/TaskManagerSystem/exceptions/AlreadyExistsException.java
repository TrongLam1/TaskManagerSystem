package com.taskManagerSystem.TaskManagerSystem.exceptions;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String mess) {
        super(mess + " already exists.");
    }
}
