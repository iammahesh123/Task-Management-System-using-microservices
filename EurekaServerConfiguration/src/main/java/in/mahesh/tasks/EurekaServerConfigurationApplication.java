package in.mahesh.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;



@SpringBootApplication
@EnableEurekaServer
public class EurekaServerConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerConfigurationApplication.class, args);
	}

}
