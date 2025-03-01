package com.base_api.services;

import com.base_api.dto.user.LoginResponseDTO;
import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.exception.InvalidLoginException;
import com.base_api.model.User;
import com.base_api.repositories.UserRepository;
import com.base_api.services.security.PasswordEncryptionService;
import com.base_api.utils.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncryptionService passwordEncryptionService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository, PasswordEncryptionService passwordEncryptionService,
            BCryptPasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;
        this.passwordEncryptionService = passwordEncryptionService;
        this.passwordEncoder = passwordEncoder1;
    }

    public User register(UserRegistrationDTO dto) {
        String encryptedPassword = passwordEncryptionService.encryptPassword(dto.getPassword());
        User user = new User(dto.getName(), dto.getUsername(), encryptedPassword);
        user.ensureExternalId();
        return userRepository.save(user);
    }

    public LoginResponseDTO login(UserLoginDTO dto) {
        User loggedUser = userRepository.findByUsername(dto.getUsername()).filter(
                user -> passwordEncoder.matches(dto.getPassword(), user.getHashedPassword())).orElseThrow(
                () -> new InvalidLoginException("Invalid email or password"));

        String token = JwtUtils.generateToken(loggedUser.getUsername(), loggedUser.getExternalId());

        return new LoginResponseDTO(token);
    }
}
