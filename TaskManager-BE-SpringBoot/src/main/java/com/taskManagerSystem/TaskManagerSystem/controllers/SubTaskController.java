package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.dtos.SubTaskDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.subTask.SubTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.SubTaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub-task")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Sub Task Controller")
public class SubTaskController {

    private final SubTaskServiceImpl subTaskService;

    @Operation(summary = "Create new sub task.")
    @PostMapping("/create")
    public ResponseData<SubTaskDTO> createSubTask(@RequestBody SubTaskRequest request) {
        try {
            log.info("Request: Create new sub task {}", request.getTaskId());
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create new sub task",
                    subTaskService.createSubTask(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Update sub task.")
    @PostMapping("/update")
    public ResponseData<SubTaskDTO> updateSubTask(@RequestBody SubTaskRequest request) {
        try {
            log.info("Request: Update sub task {}", request.getTaskId());
            return new ResponseData<>(HttpStatus.OK.value(), "Update sub task",
                    subTaskService.updateSubTask(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
