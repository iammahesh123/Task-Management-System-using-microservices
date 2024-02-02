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

@RestController
@RequestMapping("/api/submission")
public class SubController {
	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private UserService  userService;
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping()
	public ResponseEntity<TaskSubmission> submitTask(@RequestParam String task_id,
			@RequestParam String github_link, @RequestHeader("Authorization") String jwt) throws Exception {
		
		UserDTO user = userService.getUserProfile(jwt);
		TaskSubmission sub = submissionService.submitTask(task_id,github_link, user.getId(),jwt);
		
		return new ResponseEntity<>(sub,HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskSubmission> getSubmissionById(@PathVariable String id, @RequestHeader("Authorization") String jwt) throws Exception {
		
		UserDTO user = userService.getUserProfile(jwt);
		TaskSubmission sub = submissionService.getTaskSubmissionById(id);
		
		return new ResponseEntity<>(sub,HttpStatus.CREATED);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<TaskSubmission>> getAllSubmissions( @RequestHeader("Authorization") String jwt) throws Exception {
		
		UserDTO user = userService.getUserProfile(jwt);
		List<TaskSubmission> sub = submissionService.getAllTaskSubmission();
		
		return new ResponseEntity<>(sub,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/task/{taskId}")
	public ResponseEntity<TaskSubmission> AceeptorDeclineSubmissions(@PathVariable String taskId, 
			@RequestParam("status") String status,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		UserDTO user = userService.getUserProfile(jwt);
		TaskSubmission sub = submissionService.acceptDedlineSubmission(taskId, status);
		
		return new ResponseEntity<>(sub,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<TaskSubmission>> getAllSubmissions(@PathVariable String id, @RequestHeader("Authorization") String jwt) throws Exception {
		
		UserDTO user = userService.getUserProfile(jwt);
		List<TaskSubmission> sub = submissionService.getTaskSubmissionByTaskId(id);
		
		return new ResponseEntity<>(sub,HttpStatus.CREATED);
		
	}
	
	

}
