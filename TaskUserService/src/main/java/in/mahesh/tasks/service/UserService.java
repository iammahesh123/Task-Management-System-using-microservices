package in.mahesh.tasks.service;

import in.mahesh.tasks.usermodel.User;

import java.util.List;


public interface UserService {

     User getUserProfile(String jwt);
     public List<User> getAllUser();
     
     
    	 
     }

