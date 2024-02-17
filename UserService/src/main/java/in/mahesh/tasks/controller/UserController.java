package in.mahesh.tasks.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.mahesh.tasks.model.LoginRequest;
import in.mahesh.tasks.model.User;
import in.mahesh.tasks.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableHystrix
public class UserController {
	
	
	  @Autowired 
	  UserRepository userRepository;
	  
	  @Autowired
	  PasswordEncoder passwordEncoder;
	  
	  @PostMapping("/signup") 
	 @HystrixCommand(fallbackMethod = "fallbackRegisterUser")
	  public ResponseEntity<String> createUserHandler(@RequestBody User user) { 
      String email = user.getEmail();
	  String password = user.getPassword(); 
	  User createdUser = new User();
	  createdUser.setEmail(email); 
	  createdUser.setPassword(passwordEncoder.encode(password));
	  
	  User savedUser = userRepository.save(createdUser);
	  userRepository.save(savedUser); return new
	  ResponseEntity<>("User Registration Sucess", HttpStatus.OK);
	  
	  }
	 
    
	  @PostMapping("/signin") 
	 @HystrixCommand(fallbackMethod = "fallbackLoginUser")
	  public ResponseEntity<String> sign(@RequestBody LoginRequest loginRequest) {
	  String username = loginRequest.getEmail(); 
	  String password = loginRequest.getPassword();
	  
	  // Retrieve the user from the database based on the email
      User user = userRepository.findByEmail(username);

      if (user == null) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                  .body("Invalid email or password");
      }

      // Verify the password
      if (!passwordEncoder.matches(password, user.getPassword())) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                  .body("Invalid email or password");
      }

      // You can generate a token here and return it as part of the response
      // For simplicity, let's just return a success message for now
      return ResponseEntity.ok("Login successful");

	  
 }

	  // Fallback method for user registration

 public ResponseEntity<String> fallbackRegisterUser(User user) {
		  // Lothe error or perform any necessary handling
	  System.err.println("User registration failed. Fallback method called.");
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	  .body("User Registration Failed. Please try again later."); }

	 // Fallback method for user login

	   public ResponseEntity<String> fallbackLoginUser(LoginRequest
	  loginRequest) { // Log the error or perform any necessary handling
	  System.err.println("User login failed. Fallback method called."); return
	  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	  .body("User Login Failed. Please try again later."); }


}
