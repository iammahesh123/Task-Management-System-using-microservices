package in.mahesh.tasks.submissionModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TaskDTO {
	
	private String id;
	private String title;
	private String description;
	private String imageUrl;
	private String assignedUserId;
	private TaskStatus status;
	private LocalDateTime deadline;
	private LocalDateTime createAt;
	private List<String> tags = new ArrayList<>();

}
