package in.mahesh.tasks.controllerTest;

import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


import in.mahesh.tasks.controller.AuthController;
import in.mahesh.tasks.repository.UserRepository;
import in.mahesh.tasks.service.CustomerServiceImplementation;
import in.mahesh.tasks.taskSecurityConfig.JwtProvider;


class AuthControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private CustomerServiceImplementation customUserDetails;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthController authController;

	/*
	 * @Test void testCreateUserHandler() throws Exception { User user = new User();
	 * user.setEmail("test@example.com"); user.setPassword("testPassword");
	 * user.setFullName("Test User"); user.setMobile("1234567890");
	 * user.setRole("ROLE_USER");
	 * 
	 * // when(userRepository.findByEmail(any())).thenReturn(null);
	 * when(userRepository.save(any())).thenReturn(user);
	 * when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
	 * when(authenticationManager.authenticate(any())).thenReturn(null);
	 * when(JwtProvider.generateToken(any())).thenReturn("testToken");
	 * 
	 * ResponseEntity<AuthResponse> responseEntity =
	 * authController.createUserHandler(user);
	 * 
	 * assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 * assertEquals("Register Success", responseEntity.getBody().getMessage());
	 * assertEquals("testToken", responseEntity.getBody().getJwt());
	 * assertEquals(true, responseEntity.getBody().getStatus()); }
	 */

	/*
	 * @Test void testSignin() { LoginRequest loginRequest = new LoginRequest();
	 * loginRequest.setUsername("test@example.com");
	 * loginRequest.setPassword("testPassword");
	 * 
	 * UserDetails userDetails = Mockito.mock(UserDetails.class);
	 * when(customUserDetails.loadUserByUsername(any())).thenReturn(userDetails);
	 * when(passwordEncoder.matches(any(), any())).thenReturn(true);
	 * when(authenticationManager.authenticate(any())).thenReturn(null);
	 * when(JwtProvider.generateToken(any())).thenReturn("testToken");
	 * 
	 * ResponseEntity<AuthResponse> responseEntity =
	 * authController.signin(loginRequest);
	 * 
	 * assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	 * assertEquals("Login success", responseEntity.getBody().getMessage());
	 * assertEquals("testToken", responseEntity.getBody().getJwt());
	 * assertEquals(true, responseEntity.getBody().getStatus()); }
	 */
}


