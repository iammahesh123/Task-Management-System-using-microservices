package in.mahesh.tasks.service;

import in.mahesh.tasks.repository.UserRepository;
import in.mahesh.tasks.taskSecurityConfig.JwtProvider;
import in.mahesh.tasks.usermodel.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {ōōōōōōōōōōōōōōōō

    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserProfile(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        System.out.println("Email extracted from JWT: " + email);

        User user = userRepository.findByEmail(email);
        System.out.println("User retrieved from repository: " + user);

        return user;
    }
    
    public List<User> getAllUser() {
    	return userRepository.findAll();
    	
    }
}
