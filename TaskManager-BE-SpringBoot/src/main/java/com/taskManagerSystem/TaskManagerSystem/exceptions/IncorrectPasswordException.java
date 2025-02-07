package com.taskManagerSystem.TaskManagerSystem.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Password is wrong.");
    }
}
