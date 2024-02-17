package in.mahesh.tasks.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import in.mahesh.tasks.exception.UserException;
import in.mahesh.tasks.response.ApiResponse;
import in.mahesh.tasks.service.UserService;
import in.mahesh.tasks.usermodel.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@HystrixCommand(fallbackMethod = "fallbackForGetUserProfile")
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {
		
		User user = userService.findUserProfileByJwt(jwt);
		user.setPassword(null);
		System.out.print(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public ResponseEntity<User> fallbackForGetUserProfile(String jwt, Throwable throwable) {
		// Handle the fallback logic here
		// For example, you can return a default user or a custom error message
		return new ResponseEntity<>(new User(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@HystrixCommand(fallbackMethod = "fallbackForFindUserById")
	@GetMapping("/api/users/{userId}")
	public ResponseEntity<User> findUserById(
			@PathVariable String userId,
			@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserById(userId);
		user.setPassword(null);

		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<User> fallbackForFindUserById(String userId, String jwt, Throwable throwable) {
		// Handle the fallback logic here
		// For example, you can return a default user or a custom error message
		return new ResponseEntity<>(new User(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@HystrixCommand(fallbackMethod = "fallbackForFindAllUsers")
	@GetMapping("/api/users")
	public ResponseEntity<List<User>> findAllUsers(

			@RequestHeader("Authorization") String jwt)  {

		List<User> users = userService.findAllUsers();


		return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
	}
	public ResponseEntity<List<User>> fallbackForFindAllUsers(String jwt, Throwable throwable) {
		// Handle the fallback logic here
		// For example, you can return an empty list or a custom error message
		return new ResponseEntity<>(List.of(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@GetMapping()
	public ResponseEntity<?> getUsers(@RequestHeader("Authorization") String jwt) {
		try {
			List<User> users = userService.getAllUser();
			System.out.print(users);
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			// Log the exception or handle it based on your application's requirements
			return new ResponseEntity<>("Error retrieving users", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
