package com.taskManagerSystem.TaskManagerSystem.services;

import com.taskManagerSystem.TaskManagerSystem.dtos.ActivityDTO;
import com.taskManagerSystem.TaskManagerSystem.requests.activity.ActivityRequest;

import java.util.List;

public interface IActivityService {

    ActivityDTO createActivity(ActivityRequest request);

    List<ActivityDTO> getActivities(Long taskId);
}
