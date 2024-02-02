package in.mahesh.tasks.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import in.mahesh.tasks.repository.TaskRepository;
import in.mahesh.tasks.taskModel.Task;
import in.mahesh.tasks.taskModel.TaskStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImplementation implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
    public Task create(Task task, String requestRole) throws Exception {
        if (!"ROLE_ADMIN".equals(requestRole)) {
            throw new Exception("Only admin can create tasks");
        }

        task.setStatus(TaskStatus.PENDING);
        task.setCreateAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

	@Override
	public Task getTaskById(String id) throws Exception {
		// TODO Auto-generated method stub
		return taskRepository.findById(id)
				.orElseThrow(() -> new Exception("Task not found with id " + id));
	}

	@Override
	public List<Task> getAllTasks(TaskStatus taskStatus) {
		List<Task> allTasks = taskRepository.findAll();

		List<Task> fliteredTasks = allTasks.stream().filter(
				task -> taskStatus == null || task.getStatus().name().equalsIgnoreCase(taskStatus.toString())

		).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return fliteredTasks;
	}

	@Override
	public Task updateTask(String id, Task updatedTask, String userId) throws Exception {
		Task existingTasks = getTaskById(id);
		if(updatedTask.getTitle() != null) {
			existingTasks.setTitle(updatedTask.getTitle());
		}
		if(updatedTask.getImageUrl() != null) {
			existingTasks.setImageUrl(updatedTask.getImageUrl());
		}
		if (updatedTask.getDescription() != null) {
			existingTasks.setDescription(updatedTask.getDescription());
		}
		if (updatedTask.getStatus() != null) {
			existingTasks.setStatus(updatedTask.getStatus());
		}
		if (updatedTask.getDeadline() != null) {
			existingTasks.setDeadline(updatedTask.getDeadline());
		}

		return taskRepository.save(existingTasks);
	}

	@Override
	public void deleteTask(String id) throws Exception {
		getTaskById(id);
		taskRepository.deleteById(id);

		
	}

	@Override
	public Task assignedToUser(String userId, String taskId) throws Exception {
		Task task = getTaskById(taskId);
		task.setAssignedUserId(userId);
		task.setStatus(TaskStatus.DONE);

		return taskRepository.save(task);
	}

	/*
	 * @Override public List<Task> assignedUsersTask(String userId, String
	 * taskStatus) { List<Task> allTask =
	 * taskRepository.findByAssignedUserId(userId);
	 * 
	 * List<Task> fliteredTasks = allTask.stream().filter( task -> taskStatus ==
	 * null || task.getStatus().name().equalsIgnoreCase(taskStatus.toString())
	 * 
	 * ).collect(Collectors.toList()); // TODO Auto-generated method stub return
	 * fliteredTasks; }
	 */
	
	public List<Task> assignedUsersTask(String userId, TaskStatus taskStatus) {
        List<Task> allTasks = taskRepository.findByAssignedUserId(userId);

        return allTasks.stream()
                .filter(task -> taskStatus == null || task.getStatus() == taskStatus)
                .collect(Collectors.toList());
	}

	@Override
	public Task completeTask(String taskId) throws Exception {
		Task task = getTaskById(taskId);
		task.setStatus(TaskStatus.DONE);

		// TODO Auto-generated method stub
		return taskRepository.save(task);
	}

	/*
	 * @Override public List<Task> assignedUsersTask(String userId, String
	 * taskStatus) { // TODO Auto-generated method stub return null; }
	 */


}
