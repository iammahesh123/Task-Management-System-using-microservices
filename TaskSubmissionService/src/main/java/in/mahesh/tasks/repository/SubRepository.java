package in.mahesh.tasks.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.mahesh.tasks.submissionModel.TaskSubmission;

public interface SubRepository extends MongoRepository<TaskSubmission,String>{
	
	List<TaskSubmission> findByTaskId(String taskId);

}
