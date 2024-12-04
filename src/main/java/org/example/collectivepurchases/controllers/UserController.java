package org.example.collectivepurchases.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.AuthRequestDto;
import org.example.collectivepurchases.dtos.AuthResponseDto;
import org.example.collectivepurchases.dtos.UserDto;
import org.example.collectivepurchases.facade.CollectivePurchasesFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final CollectivePurchasesFacade collectivePurchasesFacade;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthRequestDto authRequestDto) {
        AuthResponseDto authResponse = collectivePurchasesFacade.authenticateUser(authRequestDto);
        return authResponse != null
                ? ResponseEntity.ok().body(Map.of("message", "Authentication successful", "accessToken", authResponse.getAccessToken(), "refreshToken", authResponse.getRefreshToken()))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Authentication failed"));
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        return collectivePurchasesFacade.createUser(userDto)
                ? ResponseEntity.ok().body(Map.of("message", "Registration successful"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Registration failed"));
    }
}