package in.mahesh.tasks.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import in.mahesh.tasks.enums.TaskStatus;
import in.mahesh.tasks.repository.TaskRepository;
import in.mahesh.tasks.taskModel.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImplementation implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	 public TaskServiceImplementation(TaskRepository taskRepository) {
	        this.taskRepository = taskRepository;
	    }

	@Override
    public Task create(Task task, String requestRole) throws Exception {
        if (!requestRole.equals("ROLE_ADMIN")) {
            throw new Exception("Only admin can create tasks");
        }

        task.setStatus(TaskStatus.PENDING);
        task.setCreateAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

	@Override
	public Task getTaskById(String id) {
	    return taskRepository.findById(id)
	                         .orElse(null); // Return null if task is not found
	}

	 


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
		  existingTasks.setStatus(updatedTask.getStatus()); }
		 
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

	
	
	public List<Task> assignedUsersTask(String userId, TaskStatus taskStatus) {
        List<Task> allTasks = taskRepository.findByassignedUserId(userId);

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

	@Override
	public List<Task> getAllTasks(TaskStatus taskStatus, String sortByDeadline, String sortByCreatedAt) {
		// TODO Auto-generated method stub
		return null;
	}

	 public List<Task> assignedUsersTask(String userId,TaskStatus status, String sortByDeadline, String sortByCreatedAt) {
	        List<Task> allTasks = taskRepository.findByassignedUserId(userId);


	        List<Task> filteredTasks = allTasks.stream()
	                .filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))


	                .collect(Collectors.toList());


	        if (sortByDeadline != null && !sortByDeadline.isEmpty()) {
	            filteredTasks.sort(Comparator.comparing(Task::getDeadline));
	        } else if (sortByCreatedAt != null && !sortByCreatedAt.isEmpty()) {
	           // filteredTasks.sort(Comparator.comparing(Task::getCreatedAt));
	        }

	        return filteredTasks;

	    }

	


}
