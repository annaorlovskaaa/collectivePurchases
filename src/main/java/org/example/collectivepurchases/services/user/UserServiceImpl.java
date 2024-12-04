package org.example.collectivepurchases.services.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.collectivepurchases.models.User;
import org.example.collectivepurchases.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User getUserByToken(String token) {
        Long userId = jwtUtil.getUserIdFromToken(token);
        return userRepository.findById(userId).orElse(null);
    }

}
