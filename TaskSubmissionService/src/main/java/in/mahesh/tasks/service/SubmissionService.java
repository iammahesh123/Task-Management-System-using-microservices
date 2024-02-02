package in.mahesh.tasks.service;

import java.util.List;

import in.mahesh.tasks.submissionModel.TaskSubmission;

public interface SubmissionService {

	TaskSubmission submitTask(String taskId, String githubLink, String userId,String jwt) throws Exception;
	TaskSubmission getTaskSubmissionById(String submissionId) throws Exception;
	List<TaskSubmission> getAllTaskSubmission();
	List<TaskSubmission> getTaskSubmissionByTaskId(String taskId);
	TaskSubmission acceptDedlineSubmission(String id, String status) throws Exception;
}
