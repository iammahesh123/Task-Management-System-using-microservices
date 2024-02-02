package in.mahesh.tasks.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.mahesh.tasks.repository.SubRepository;
import in.mahesh.tasks.submissionModel.TaskDTO;
import in.mahesh.tasks.submissionModel.TaskSubmission;

@Service
public class SubServiceImp implements SubmissionService {

	@Autowired
	private SubRepository subRepository;

	@Autowired
	private TaskService taskService;
	

	@Override
	public TaskSubmission submitTask(String taskId, String githubLink, String userId, String jwt) throws Exception {
		TaskDTO task = taskService.getTaskById(taskId, jwt);
		if (task != null) {
			TaskSubmission sub = new TaskSubmission();
			sub.setTaskId(taskId);
			sub.setUserId(userId);
			sub.setGithubLink(githubLink);
			sub.setSubmissionTime(LocalDateTime.now());
			return subRepository.save(sub);
		}
		throw new Exception("Task not found with this id" + taskId);

	}

	@Override
	public TaskSubmission getTaskSubmissionById(String submissionId) throws Exception {
		// TODO Auto-generated method stub
		return subRepository.findById(submissionId)
				.orElseThrow(() -> new Exception("Task submission is not found with id" + submissionId));
	}

	@Override
	public List<TaskSubmission> getAllTaskSubmission() {
		// TODO Auto-generated method stub
		return subRepository.findAll();
	}

	@Override
	public List<TaskSubmission> getTaskSubmissionByTaskId(String taskId) {
		// TODO Auto-generated method stub
		return subRepository.findByTaskId(taskId);
	}

	@Override
	public TaskSubmission acceptDedlineSubmission(String id, String status) throws Exception {
		TaskSubmission sub = getTaskSubmissionById(id);
		sub.setStatus(status);
		if (status.equals("ACCEPT")) {
			taskService.completeTask(sub.getTaskId());

		}

		return subRepository.save(sub);
	}

}
