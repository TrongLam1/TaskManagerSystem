package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.dtos.TaskDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.CreateTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.requests.UpdateTaskRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.PaginationResult;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostMapping("/create")
    public ResponseData<TaskDTO> createNewTask(@RequestBody CreateTaskRequest request) {
        try {
            log.info("Request: Create new task");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create new task",
                    taskService.createTask(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/duplicate")
    public ResponseData<TaskDTO> duplicateTask(@RequestBody Long taskId) {
        try {
            log.info("Request: Duplicate task {}", taskId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Duplicate task",
                    taskService.duplicateTask(taskId));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseData<TaskDTO> updateTask(@RequestBody UpdateTaskRequest request) {
        try {
            log.info("Request: Update task {}", request.getTaskId());
            return new ResponseData<>(HttpStatus.CREATED.value(), "Update task",
                    taskService.updateTask(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/remove/{taskId}")
    public ResponseData<String> removeTask(@PathVariable Long taskId) {
        try {
            log.info("Request: Remove task {}", taskId);
            taskService.removeTask(taskId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Remove task",
                    "Remove task successfully.");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/restore/{taskId}")
    public ResponseData<String> restoreTask(@PathVariable Long taskId) {
        try {
            log.info("Request: Restore task {}", taskId);
            taskService.restoreTask(taskId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Restore task",
                    "Restore task successfully.");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseData<String> deleteTask(@PathVariable Long taskId) {
        try {
            log.info("Request: Delete task {}", taskId);
            taskService.deleteTask(taskId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Delete task",
                    "Delete task successfully.");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/get/{taskId}")
    public ResponseData<TaskDTO> getTask(@PathVariable Long taskId) {
        try {
            log.info("Request: Get task {}", taskId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get task",
                    taskService.getTask(taskId));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseData<PaginationResult<TaskDTO>> getTasksByStatus(
            @RequestParam("isRemoved") boolean isRemoved,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("sortDirection") String sortDirection) {
        try {
            log.info("Request: Get tasks by status {}", isRemoved);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get tasks",
                    taskService.getTasksByStatus(isRemoved, pageNo, pageSize, sortBy, sortDirection));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
