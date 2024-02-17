package in.mahesh.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@SpringBootApplication
@EnableHystrix
public class TaskUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskUserServiceApplication.class, args);
	}

}
