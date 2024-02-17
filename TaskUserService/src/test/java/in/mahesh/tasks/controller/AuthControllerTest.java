package in.mahesh.tasks.controller;

import in.mahesh.tasks.exception.UserException;
import in.mahesh.tasks.repository.UserRepository;
import in.mahesh.tasks.request.LoginRequest;
import in.mahesh.tasks.response.AuthResponse;
import in.mahesh.tasks.usermodel.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUserHandler_Success() throws UserException {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFullName("Test User");
        user.setMobile("1234567890");
        user.setRole("USER");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        ResponseEntity<AuthResponse> responseEntity = authController.createUserHandler(user);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        AuthResponse authResponse = responseEntity.getBody();
        assertNotNull(authResponse);
        assertTrue(authResponse.getStatus());
        assertEquals("Register Success", authResponse.getMessage());
        assertNotNull(authResponse.getJwt());
    }

    @Test
    void testCreateUserHandler_EmailAlreadyExists() {
        // Arrange
        User existingUser = new User();
        existingUser.setEmail("test@example.com");

        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(existingUser);

        // Act & Assert
        assertThrows(UserException.class, () -> authController.createUserHandler(existingUser));
    }

    @Test
    void testSignin_Success() {
        // Arrange
        String username = "test@example.com";
        String password = "password";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setemail(username);
        loginRequest.setPassword(password);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
                .thenReturn(authentication);

        // Act
        ResponseEntity<AuthResponse> responseEntity = authController.signin(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        AuthResponse authResponse = responseEntity.getBody();
        assertNotNull(authResponse);
        assertEquals("Login success", authResponse.getMessage());
        assertNotNull(authResponse.getJwt());
    }

    @Test
    void testSignin_InvalidCredentials() {
        // Arrange
        String username = "test@example.com";
        String password = "wrongPassword";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setemail(username);
        loginRequest.setPassword(password);

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
                .thenThrow(new BadCredentialsException("Invalid username and password"));

        // Act & Assert
        assertThrows(BadCredentialsException.class, () -> authController.signin(loginRequest));
    }
}

