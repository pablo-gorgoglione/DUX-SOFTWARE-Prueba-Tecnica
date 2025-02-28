package com.base_api.services.security;

import com.base_api.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getHashedPassword(),
                        Collections.emptyList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDetails loadByUserExternalId(String userExternalId) throws UsernameNotFoundException {
        return userRepository.findByExternalId(userExternalId)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getHashedPassword(),
                        Collections.emptyList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
