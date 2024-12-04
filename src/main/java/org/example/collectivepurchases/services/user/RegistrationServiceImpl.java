package org.example.collectivepurchases.services.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.UserDto;
import org.example.collectivepurchases.models.User;
import org.example.collectivepurchases.models.enums.Role;
import org.example.collectivepurchases.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;

    @Override
    public boolean createUser(UserDto userDTO) {
        String email = userDTO.getEmail();
        if (isEmailAlreadyInUse(email)) {
            log.warn("Email already in use: {}", email);
            return false;
        }
        User user = buildUserFromDto(userDTO);
        userRepository.save(user);
        log.info("Saving new user with : {}", email);
        return true;
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        boolean emailExists = userRepository.findByEmail(email).isPresent();
        if (emailExists) {
            log.warn("Email already in use: {}", email);
        }
        return emailExists;
    }

    private User buildUserFromDto(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()))
                .role(Role.USER)
                .isActive(true)
                .build();
    }
}