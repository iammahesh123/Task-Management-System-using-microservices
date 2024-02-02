package in.mahesh.tasks.controller;

import in.mahesh.tasks.service.TaskService;
import in.mahesh.tasks.service.UserService;
import in.mahesh.tasks.taskModel.Task;
import in.mahesh.tasks.taskModel.TaskStatus;
import in.mahesh.tasks.taskModel.UserDTO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	// create Task API

	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt)
			throws Exception {
		UserDTO user = userService.getUserProfile(jwt);
		Task createdTask = taskService.create(task, user.getRole());

		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable String id, @RequestHeader("Authorization") String jwt)
			throws Exception {
		UserDTO user = userService.getUserProfile(jwt);
		Task task = taskService.getTaskById(id);

		return new ResponseEntity<>(task, HttpStatus.OK);

	}

	@GetMapping("/user")
	public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required = false) TaskStatus status,
			@RequestHeader("Authorization") String jwt) throws Exception {
		UserDTO user = userService.getUserProfile(jwt);
		List<Task> tasks = taskService.assignedUsersTask(user.getId(), status);
		return new ResponseEntity<>(tasks, HttpStatus.OK);

	}

	/*
	 * Test with Posman with Post Method http://localhost:8082/api/tasks requestBody
	 * and gobal config with JWT { "title":"New Website Creation using springBoot",
	 * "image":"https://www.image.com/dhdbhjf.png",
	 * "description":"Do it this Website as soon as possible ",
	 * "deadline":"2024-02-29T12:34:38.9056991 " } reponse { "id":
	 * "65b913d02071402a82b2f9b8", "title": "New Website Creation using springBoot",
	 * "description": "Do it this Website as soon as possible ", "imageUrl": null,
	 * "assignedUserId": 0, "status": "PENDING", "deadline":
	 * "2024-02-29T12:34:38.9056991", "createAt": "2024-01-30T20:50:48.2276611" }
	 */

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) TaskStatus status,
			@RequestHeader("Authorization") String jwt) throws Exception {
		UserDTO user = userService.getUserProfile(jwt);
		List<Task> tasks = taskService.getAllTasks(status);

		return new ResponseEntity<>(tasks, HttpStatus.OK);

	}

	@PutMapping("/{id}/user/{userId}/assigned")
	public ResponseEntity<Task> assignTaskToUser(@PathVariable String id, @PathVariable String userId,
	                                             @RequestHeader("Authorization") String jwt) throws Exception {
	   UserDTO user = userService.getUserProfile(jwt);
	   Task task = taskService.assignedToUser(id, userId);

	    return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task req,
	        @RequestHeader("Authorization") String jwt) throws Exception {
	    UserDTO user = userService.getUserProfile(jwt);
	    Task updatedTask = taskService.updateTask(id, req, user.getId());
	    
	    // Use a logger for debugging instead of System.out.print
	   // logger.debug("Updated Task: {}", updatedTask);

	    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}


	@PutMapping("/{id}/complete")
	public ResponseEntity<Task> completeTask(@PathVariable String id) throws Exception {

		Task tasks = taskService.completeTask(id);

		return new ResponseEntity<>(tasks, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable String id) throws Exception {

		taskService.deleteTask(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
