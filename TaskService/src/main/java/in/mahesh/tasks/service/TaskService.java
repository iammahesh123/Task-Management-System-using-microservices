package in.mahesh.tasks.service;

import java.util.List;

import in.mahesh.tasks.taskModel.Task;
import in.mahesh.tasks.taskModel.TaskStatus;

public interface TaskService {
	Task create(Task task, String requestRole) throws Exception;
	
	Task getTaskById(String id) throws Exception;
	
	List<Task> getAllTasks(TaskStatus taskStatus);
	
	Task updateTask(String id, Task updatedTask, String userId) throws Exception;
	
	void deleteTask(String id) throws Exception;
	
	Task assignedToUser(String id, String taskId) throws Exception;
	
	List<Task> assignedUsersTask(String userId, TaskStatus taskStatus);
	
	Task completeTask(String taskId) throws Exception;
	
	

}
