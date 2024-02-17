package in.mahesh.tasks.controller;

import in.mahesh.tasks.controller.UserController;
import in.mahesh.tasks.exception.UserException;
import in.mahesh.tasks.service.UserService;
import in.mahesh.tasks.usermodel.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserProfile() throws UserException {
        // Arrange
        String jwt = "mockJWT";
        User mockUser = new User();
        // Set up mock behavior
        when(userService.findUserProfileByJwt(jwt)).thenReturn(mockUser);

        // Act
        ResponseEntity<User> responseEntity = userController.getUserProfile(jwt);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUser, responseEntity.getBody());
        assertNull(responseEntity.getBody().getPassword()); // Ensure password is null
    }

    @Test
    void testFindUserById() throws UserException {
        // Arrange
        String userId = "mockUserId";
        String jwt = "mockJWT";
        User mockUser = new User();
        // Set up mock behavior
        when(userService.findUserById(userId)).thenReturn(mockUser);

        // Act
        ResponseEntity<User> responseEntity = userController.findUserById(userId, jwt);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(mockUser, responseEntity.getBody());
        assertNull(responseEntity.getBody().getPassword()); // Ensure password is null
    }

    @Test
    void testFindAllUsers() {
        // Arrange
        String jwt = "mockJWT";
        List<User> mockUsers = new ArrayList<>();
        // Set up mock behavior
        when(userService.findAllUsers()).thenReturn(mockUsers);

        // Act
        ResponseEntity<List<User>> responseEntity = userController.findAllUsers(jwt);

        // Assert
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(mockUsers, responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().size()); // Ensure the list is empty
    }

    @Test
    void testGetUsers() {
        // Arrange
        String jwt = "mockJWT";
        List<User> mockUsers = new ArrayList<>();
        // Set up mock behavior
        try {
			when(userService.getAllUser()).thenReturn(mockUsers);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Act
        ResponseEntity<?> responseEntity = userController.getUsers(jwt);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUsers, responseEntity.getBody());
    }
}

