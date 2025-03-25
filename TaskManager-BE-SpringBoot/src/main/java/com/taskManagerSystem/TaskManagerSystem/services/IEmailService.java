package com.taskManagerSystem.TaskManagerSystem.services;

public interface IEmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
