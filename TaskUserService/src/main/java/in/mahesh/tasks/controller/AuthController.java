package in.mahesh.tasks.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import in.mahesh.tasks.exception.UserException;
import in.mahesh.tasks.repository.UserRepository;
import in.mahesh.tasks.request.LoginRequest;
import in.mahesh.tasks.response.AuthResponse;
import in.mahesh.tasks.service.CustomerServiceImplementation;
import in.mahesh.tasks.service.UserService;
import in.mahesh.tasks.taskSecurityConfig.JwtProvider;
import in.mahesh.tasks.usermodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

   
    @Autowired
    private CustomerServiceImplementation customUserDetails;
    
    @Autowired
    private UserService userService;



    @HystrixCommand(fallbackMethod = "createUserFallback")
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String mobile = user.getMobile();
        String role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);
        if (isEmailExist != null) {
        	throw new UserException("Email Is Already Used With Another Account");

        }
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setMobile(mobile);
        createdUser.setRole(role);
        createdUser.setPassword(passwordEncoder.encode(password));
        
		User savedUser = userRepository.save(createdUser);
          userRepository.save(savedUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);


        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }
    public ResponseEntity<AuthResponse> createUserFallback(User user, Throwable throwable) {
        // Handle the fallback logic here
        // You can return a default response or log the error
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("User registration failed due to a temporary issue.");
        authResponse.setStatus(false);
        return new ResponseEntity<>(authResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @HystrixCommand(fallbackMethod = "signinFallback")
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getemail();
        String password = loginRequest.getPassword();

        System.out.println(username+"-------"+password);

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }
    public ResponseEntity<AuthResponse> signinFallback(LoginRequest loginRequest, Throwable throwable) {
        // Handle the fallback logic here
        // You can return a default response or log the error
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Login failed due to a temporary issue.");
        authResponse.setStatus(false);
        return new ResponseEntity<>(authResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    
    private Authentication authenticate(String username, String password) {

        System.out.println(username+"---++----"+password);

        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("Sig in in user details"+ userDetails);

        if(userDetails == null) {
            System.out.println("Sign in details - null" + userDetails);

            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            System.out.println("Sign in userDetails - password mismatch"+userDetails);

            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }



}
