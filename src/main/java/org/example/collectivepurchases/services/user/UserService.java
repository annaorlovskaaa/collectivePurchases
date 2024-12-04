package org.example.collectivepurchases.services.user;

import org.example.collectivepurchases.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserById(Long id);

    List<User> getAllUsers();

    boolean userExists(Long userId);

    User getUserByToken(String token);
}
