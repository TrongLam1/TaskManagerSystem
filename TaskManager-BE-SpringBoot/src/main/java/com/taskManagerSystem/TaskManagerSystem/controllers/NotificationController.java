package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.dtos.NotificationDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.notify.NotificationRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.NotificationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifies")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Notification Controller")
public class NotificationController {

    private final NotificationServiceImpl notificationService;

    @Operation(summary = "Create new notification.")
    @PostMapping("/create")
    public ResponseData<NotificationDTO> createNotification(@RequestBody NotificationRequest request) {
        try {
            log.info("Request: Create new notification for task {}", request.getTaskId());
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create new notification",
                    notificationService.createNotification(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Get all notifications.")
    @PostMapping("/get-all")
    public ResponseData<PaginationResult<NotificationDTO>> getAllNotifications(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize
    ) {
        try {
            log.info("Request: Get all notifications");
            return new ResponseData<>(HttpStatus.OK.value(), "Get all notifications",
                    notificationService.getNotifications(pageNo, pageSize));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Get limit notifications.")
    @PostMapping("/limit/{limit}")
    public ResponseData<List<NotificationDTO>> getLimitNotifications(@Param("limit") int limit) {
        try {
            log.info("Request: Get limit {} notifications", limit);
            return new ResponseData<>(HttpStatus.OK.value(), "Get limit notifications",
                    notificationService.getLimitNewNotifications(limit));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
