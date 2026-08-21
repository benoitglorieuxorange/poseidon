package com.nnk.springboot.security;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User loading service for Spring Security.
 *
 * This class retrieves a user from its login name and converts it into a
 * {@link UserDetails} instance usable by the authentication mechanism.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Builds the service with the user repository.
     *
     * @param userRepository the repository used to look up users
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by username.
     *
     * This method queries the database, throws an exception if no user is
     * found, and then returns a {@link CustomUserDetails} instance for
     * authentication.
     *
     * @param username the username entered during login
     * @return the security details associated with the matching user
     * @throws UsernameNotFoundException if no user matches the provided username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new CustomUserDetails(user);
    }
}