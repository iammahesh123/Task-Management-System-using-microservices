package in.mahesh.tasks.controller;

import in.mahesh.tasks.enums.TaskStatus;
import in.mahesh.tasks.service.TaskService;
import in.mahesh.tasks.service.UserService;
import in.mahesh.tasks.taskModel.Task;
import in.mahesh.tasks.taskModel.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	  private TaskService taskService;
	    private UserService userService;

	    @Autowired
	    public TaskController(TaskService taskService, UserService userService) {
	        this.taskService = taskService;
	        this.userService=userService;
	    }

	// create Task API

	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt)
			throws Exception {
		
		 if(jwt==null){
	            throw new Exception("jwt required...");
	        }
		UserDTO user = userService.getUserProfileHandler(jwt);
		Task createdTask = taskService.create(task, user.getRole());

		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable String id, @RequestHeader("Authorization") String jwt)
			throws Exception {
		 if(jwt==null){
	            throw new Exception("jwt required...");
	        }
		
		//UserDTO user = userService.getUserProfile(jwt);
		Task task = taskService.getTaskById(id);

		//return new ResponseEntity<>(task, HttpStatus.OK);
		return task != null ? new ResponseEntity<>(task, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	 @GetMapping("/user")
	    public ResponseEntity< List<Task> > getAssignedUsersTask(
	            @RequestHeader("Authorization") String jwt,
	            @RequestParam(required = false) TaskStatus status,
	            @RequestParam(required = false) String sortByDeadline,
	            @RequestParam(required = false) String sortByCreatedAt) throws Exception {
	        UserDTO user=userService.getUserProfileHandler(jwt);
	        List<Task> tasks = taskService.assignedUsersTask(user.getId(),status, sortByDeadline, sortByCreatedAt);
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

	

	 @GetMapping
	    public ResponseEntity<List<Task>> getAllTasks(
	            @RequestHeader("Authorization") String jwt,
	            @RequestParam(required = false) TaskStatus status,
	            @RequestParam(required = false) String sortByDeadline,
	            @RequestParam(required = false) String sortByCreatedAt
	    ) throws Exception {
	        if(jwt==null){
	            throw new Exception("jwt required...");
	        }
	        List<Task> tasks = taskService.getAllTasks(status, sortByDeadline, sortByCreatedAt);
	        return new ResponseEntity<>(tasks, HttpStatus.OK);
	    }
	 
	    @PutMapping("/{id}/user/{userId}/assigned")
	    public ResponseEntity<Task> assignedTaskToUser(
	            @PathVariable String id,
	            @PathVariable String userId,
	            @RequestHeader("Authorization") String jwt
	    ) throws Exception {
	        UserDTO user=userService.getUserProfileHandler(jwt);
	        Task task = taskService.assignedToUser(userId,id);
	        return new ResponseEntity<>(task, HttpStatus.OK);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Task> updateTask(
	            @PathVariable String id,
	            @RequestBody Task req,
	            @RequestHeader("Authorization") String jwt
	    ) throws Exception {
	        if(jwt==null){
	            throw new Exception("jwt required...");
	        }
	        UserDTO user=userService.getUserProfileHandler(jwt);
	        Task task = taskService.updateTask(id, req, user.getId());
	        return task != null ? new ResponseEntity<>(task, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
	        try {
				taskService.deleteTask(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @PutMapping("/{id}/complete")
	    public ResponseEntity<Task> completeTask(@PathVariable String id) throws Exception {
	        Task task = taskService.completeTask(id);
	        return new ResponseEntity<>(task, HttpStatus.NO_CONTENT);
	    }
}
