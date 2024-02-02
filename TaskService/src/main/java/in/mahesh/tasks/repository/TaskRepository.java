package in.mahesh.tasks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.mahesh.tasks.taskModel.Task;

import java.util.List;

@Repository

public interface TaskRepository extends MongoRepository<Task,String> {
    public List<Task> findByAssignedUserId(String userId);

	

}
