package com.daily4work.daily22.security;

import com.daily4work.daily22.entity.User;
import com.daily4work.daily22.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        if (username.matches("\\d+")) {
            user = userRepository.findById(Integer.parseInt(username))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + username));
        } else {
            user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        }

        return new CustomUserDetails(user);
    }
}
