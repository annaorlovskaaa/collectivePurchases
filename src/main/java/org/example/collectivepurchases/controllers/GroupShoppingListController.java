package org.example.collectivepurchases.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.AddUserRequest;
import org.example.collectivepurchases.dtos.GroupShoppingListDto;
import org.example.collectivepurchases.facade.CollectivePurchasesFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group-shopping-lists")
@AllArgsConstructor
@Slf4j
public class GroupShoppingListController {

    private final CollectivePurchasesFacade collectivePurchasesFacade;

    @PostMapping("/create")
    public ResponseEntity<Object> createGroupShoppingList(@RequestHeader("Authorization") String token, @RequestBody GroupShoppingListDto groupShoppingListDto) {
        Boolean result = collectivePurchasesFacade.createGroupShoppingList(token, groupShoppingListDto);
        return result
                ? ResponseEntity.ok().body(Map.of("message", "Group shopping list created successfully"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Failed to create group shopping list"));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllGroupShoppingLists() {
        List<GroupShoppingListDto> lists = collectivePurchasesFacade.getAllGroupShoppingLists();
        return lists != null && !lists.isEmpty()
                ? ResponseEntity.ok().body(Map.of("message", "Group shopping lists retrieved successfully", "groupShoppingLists", lists))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No group shopping lists found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGroupShoppingListById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        GroupShoppingListDto list = collectivePurchasesFacade.getGroupShoppingListById(id, token);
        log.info("Retrieved group shopping list: {}", list);
        return list != null
                ? ResponseEntity.ok().body(Map.of("message", "Group shopping list retrieved successfully", "groupShoppingList", list))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Group shopping list not found"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateGroupShoppingList(@PathVariable Long id, @RequestBody GroupShoppingListDto groupShoppingListDto, @RequestHeader("Authorization") String token) {
        Boolean result = collectivePurchasesFacade.updateGroupShoppingList(id, groupShoppingListDto, token);
        return result
                ? ResponseEntity.ok().body(Map.of("message", "Group shopping list updated successfully"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Failed to update group shopping list"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteGroupShoppingList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Boolean result = collectivePurchasesFacade.deleteGroupShoppingList(id, token);
        return result
                ? ResponseEntity.ok().body(Map.of("message", "Group shopping list deleted successfully"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Failed to delete group shopping list"));
    }

    @PostMapping("/{groupShoppingListId}/users/add")
    public ResponseEntity<Object> addUserToGroupShoppingList(@RequestHeader("Authorization") String token, @PathVariable Long groupShoppingListId, @RequestBody AddUserRequest request) {
        return collectivePurchasesFacade.addUserToGroupShoppingList(groupShoppingListId, request.getUserId(), token)
                ? ResponseEntity.ok().body(Map.of("message", "User added to group shopping list successfully"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Failed to add user to group shopping list"));
    }

}