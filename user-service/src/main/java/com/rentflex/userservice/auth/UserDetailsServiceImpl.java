package com.rentflex.userservice.auth;

import com.rentflex.userservice.model.User;
import com.rentflex.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =
                userRepository
                        .findByEmail(username)
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "User not found with email: " + username));

        if (!"ACTIVE".equalsIgnoreCase(String.valueOf(user.getStatus()))) {
            throw new RuntimeException("Account is deactivated. Please contact support");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // required even for JWT
                .authorities("ROLE_" + user.getRole()) // e.g. USER / ADMIN
                .build();
    }
}
