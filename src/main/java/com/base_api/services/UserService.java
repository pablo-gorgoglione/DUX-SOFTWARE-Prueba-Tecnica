package com.base_api.services;

import com.base_api.configurations.JwtAuthenticationToken;
import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.exception.InvalidLoginException;
import com.base_api.model.User;
import com.base_api.repositories.UserRepository;
import com.base_api.services.security.PasswordEncryptionService;
import com.base_api.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncryptionService passwordEncryptionService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository, PasswordEncryptionService passwordEncryptionService,
            BCryptPasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;
        this.passwordEncryptionService = passwordEncryptionService;
        this.passwordEncoder = passwordEncoder1;
    }

    public User register(UserRegistrationDTO dto) {
        String encryptedPassword = passwordEncryptionService.encryptPassword(dto.getPassword());
        User user = new User(dto.getName(), dto.getEmail(), encryptedPassword);
        user.ensureExternalId();
        return userRepository.save(user);
    }

    public String login(UserLoginDTO dto) {

        User loggedUser = userRepository.findByEmail(dto.getEmail()).filter(
                        user -> passwordEncoder.matches(dto.getPassword(), user.getHashedPassword()))
                .orElseThrow(() -> new InvalidLoginException("Invalid email or password"));

        return JwtUtils.generateToken(loggedUser.getEmail(), loggedUser.getExternalId());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByExternaId(String externalId) {
        return userRepository.findByExternalId(externalId);
    }

    public String getLoggedUsername() {
        return getLoggedUser().getName();
    }

    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            String externalId = ((JwtAuthenticationToken) authentication).getExternalId();

            return findByExternaId(externalId).orElseThrow(
                    () -> new IllegalArgumentException("User not found")
            );
        }
        throw new IllegalArgumentException("Token is null");
    }
}
