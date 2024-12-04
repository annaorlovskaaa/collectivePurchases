package org.example.collectivepurchases.services.user;

import org.example.collectivepurchases.dtos.AuthRequestDto;
import org.example.collectivepurchases.dtos.AuthResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);
}