package org.example.collectivepurchases.dtos;

import lombok.Builder;
import lombok.Data;
import org.example.collectivepurchases.models.enums.Role;


@Data
@Builder
public class AuthRequestDto {
    private String email;
    private String password;
    private Role role;
}
