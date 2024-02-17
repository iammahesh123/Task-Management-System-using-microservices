package in.mahesh.tasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.mahesh.tasks.service.SubmissionService;
import in.mahesh.tasks.service.TaskService;
import in.mahesh.tasks.service.UserService;
import in.mahesh.tasks.submissionModel.TaskSubmission;
import in.mahesh.tasks.submissionModel.UserDTO;
import jakarta.inject.Qualifier;



@RestController
@RequestMapping("/api/submissions")
public class SubController {



   
	/*
	 * @Autowired private TaskService taskService;
	 Mapping()
    public ResponseEntity<TaskSubmission> submitTask(@RequestParam String task_id,
                                                      @RequestParam String github_link, @RequestHeader("Authorization") String jwt) throws Exception {
        try {
            UserDTO user = userService.getUserProfileHandler(jwt);
            TaskSubmission sub = submissionService.submitTask(task_id, github_link, user.getId(), jwt);

            return new ResponseEntity<>(sub, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Re-throwing the exception to propagate it up
        }
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<TaskSubmission> getTaskSubmissionById(@PathVariable String id) throws Exception {
        try {
            TaskSubmission sub = submissionService.getTaskSubmissionById(id);
            return new ResponseEntity<>(sub, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Re-throwing the exception to propagate it up
        }
    }

    @GetMapping()
    public ResponseEntity<List<TaskSubmission>> getAllTaskSubmissions() throws Exception {
        try {
            List<TaskSubmission> sub = submissionService.getAllTaskSubmissions();
            return new ResponseEntity<>(sub, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Re-throwing the exception to propagate it up
        }
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<TaskSubmission>> getTaskSubmissionsByTaskId(@PathVariable String taskId) {
        try {
            List<TaskSubmission> submissions = submissionService.getTaskSubmissionByTaskId(taskId);
            return new ResponseEntity<>(submissions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Re-throwing the exception to propagate it up
        }
    }

	/*
	 * @PutMapping("/{id}") public ResponseEntity<TaskSubmission>
	 * acceptOrDeclineTaskSubmission(
	 * 
	 * @PathVariable String id,
	 * 
	 * @RequestParam("status") String status) throws Exception { try {
	 * TaskSubmission submission = submissionService.acceptDeclineSubmission(id,
	 * status);
	 * 
	 * if (submission.getStatus().equals("COMPLETE")) {
	 * taskService.completeTask(submission.getTaskId()); }
	 * 
	 * return new ResponseEntity<>(submission, HttpStatus.OK); } catch (Exception e)
	 * { e.printStackTrace(); throw e; // Re-throwing the exception to propagate it
	 * up } }
	 */
}

