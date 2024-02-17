package in.mahesh.tasks.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mahesh.tasks.service.UserService;

@RestController
public class HomeController {
	
	


    @GetMapping("/tasks")
    public ResponseEntity<String> HomeController() {


        return new ResponseEntity<>("Welcome to task Service", HttpStatus.OK);

    }
}
