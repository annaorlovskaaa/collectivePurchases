package org.example.collectivepurchases.services.user;

import org.example.collectivepurchases.dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    boolean createUser(UserDto userDTO);
    boolean isEmailAlreadyInUse(String email);
}