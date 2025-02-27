package com.base_api.services;

import com.base_api.dto.user.UserLoginDTO;
import com.base_api.dto.user.UserRegistrationDTO;
import com.base_api.model.User;
import com.base_api.repositories.UserRepository;
import com.base_api.services.security.PasswordEncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncryptionService passwordEncryptionService;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncryptionService passwordEncryptionService, PasswordEncoder passwordEncoder,
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

  public User login(UserLoginDTO dto) {
    return userRepository.findByEmail(dto.getEmail()).filter(user -> passwordEncoder.matches(dto.getPassword(), user.getHashedPassword()))
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
