package org.example.collectivepurchases.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.FriendshipDto;
import org.example.collectivepurchases.facade.CollectivePurchasesFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/friendships")
@Slf4j
public class FriendshipController {
    private final CollectivePurchasesFacade collectivePurchasesFacade;

    @PostMapping("/add")
    public ResponseEntity<Object> addFriendship(@RequestBody FriendshipDto friendshipDto) {
        return collectivePurchasesFacade.addFriendship(friendshipDto)
                ? ResponseEntity.ok().body(Map.of("message", "Friendship added successfully"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Failed to add friendship"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFriendship(@RequestBody Map<String, Long> payload) {
        Long userId = payload.get("userId");
        Long friendId = payload.get("friendId");
        return collectivePurchasesFacade.deleteFriendship(userId, friendId)
                ? ResponseEntity.ok().body(Map.of("message", "Friendship deleted successfully"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Failed to delete friendship"));
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<Object> getFriendshipsByUserId(@PathVariable Long userId) {
        List<FriendshipDto> friendships = collectivePurchasesFacade.getFriendshipsByUserId(userId);
        return friendships.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No friendships found"))
                : ResponseEntity.ok(Map.of("friendships", friendships));
    }
}