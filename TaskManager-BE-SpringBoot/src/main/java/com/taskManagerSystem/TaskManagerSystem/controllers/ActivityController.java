package com.taskManagerSystem.TaskManagerSystem.controllers;

import com.taskManagerSystem.TaskManagerSystem.dtos.ActivityDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.activity.ActivityRequest;
import com.taskManagerSystem.TaskManagerSystem.responses.ResponseData;
import com.taskManagerSystem.TaskManagerSystem.services.impl.ActivityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Activity Controller")
public class ActivityController {

    private final ActivityServiceImpl activityService;

    @Operation(summary = "Create new activity.")
    @PostMapping("/create")
    public ResponseData<ActivityDTO> createActivity(@RequestBody ActivityRequest request) {
        try {
            log.info("Request: Create new activity {}", request.getActivity());
            return new ResponseData<>(HttpStatus.CREATED.value(), "Create new activity",
                    activityService.createActivity(request));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Get task's list activities.")
    @GetMapping("/get-all/{taskId}")
    public ResponseData<List<ActivityDTO>> getActivities(@RequestParam("taskId") Long taskId) {
        try {
            log.info("Request: Get task's list activities {}", taskId);
            return new ResponseData<>(HttpStatus.OK.value(), "Get task's list activities",
                    activityService.getActivities(taskId));
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
