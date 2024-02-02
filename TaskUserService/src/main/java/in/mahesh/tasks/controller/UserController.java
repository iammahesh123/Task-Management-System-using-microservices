package in.mahesh.tasks.controller;

import in.mahesh.tasks.service.UserService;
import in.mahesh.tasks.usermodel.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * http://localhost:8081/api/user Take login JWT token in bearer token
	 * eyJhbGciOiJIUzI1NiJ9.
	 * eyJpYXQiOjE3MDY2MjUzNjcsImV4cCI6MTcwNjcxMTc2NywiZW1haWwiOiJtYWh
	 * lc2hrbTUwMDFAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOiIifQ.HLL-
	 * WbSpsgBJQSErID0B3ICUtPu915SbSRLQ9BX60gg Output response: [ { "fullName":
	 * "mahesh Kadambala", "email": "maheshkm5001@gmail.com", "password":
	 * "$2a$10$pR26KjYca3F0D0QLoJEwaOCezAUBiSJuVb5v/4CfcDue2LsHFfZde", "role":
	 * "Customer Role", "mobile": "9573327520", "_id": "65b908e0e26b2f52e437f06f" }
	 * ]
	 */

	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) {
		User user = userService.getUserProfile(jwt);
		System.out.print(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
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
