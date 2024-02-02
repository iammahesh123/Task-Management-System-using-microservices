package in.mahesh.tasks.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import in.mahesh.tasks.submissionModel.TaskDTO;

@FeignClient(name = "task-SERVICE",url = "http://localhost:8081")
public interface TaskService {

    @GetMapping("/api/tasks/{id}")
    TaskDTO getTaskById(@PathVariable("id") String id, @RequestHeader("Authorization") String jwt)
            throws Exception;

    @PutMapping("/api/tasks/{id}/complete")
    TaskDTO completeTask(@PathVariable("id") String id) throws Exception;
}
