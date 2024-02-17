package in.mahesh.tasks.service;

import in.mahesh.tasks.repository.UserRepository;


import in.mahesh.tasks.usermodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    public CustomerServiceImplementation(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        System.out.println(user);
       
        if(user==null) {
            throw new UsernameNotFoundException("User not found with this email"+username);

        }

        
        System.out.println("Loaded user: " + user.getEmail() + ", Role: " + user.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
