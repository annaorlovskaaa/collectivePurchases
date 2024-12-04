package org.example.collectivepurchases.controllers;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.dtos.WishListDto;
import org.example.collectivepurchases.facade.CollectivePurchasesFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
@AllArgsConstructor
public class WishListController {

    private final CollectivePurchasesFacade collectivePurchasesFacade;

    @PostMapping
    public ResponseEntity<WishListDto> createWishList(@RequestBody WishListDto wishListDto) {
        WishListDto response = collectivePurchasesFacade.createWishList(wishListDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WishListDto>> getWishListsByUserId(@PathVariable Long userId) {
        List<WishListDto> response = collectivePurchasesFacade.getWishListsByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishListDto> getWishListById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        WishListDto response = collectivePurchasesFacade.getWishListById(id, token);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishListById(@PathVariable Long id) {
        collectivePurchasesFacade.deleteWishListById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<WishListDto> updateWishList(@RequestBody WishListDto wishListDto) {
        WishListDto response = collectivePurchasesFacade.updateWishList(wishListDto);
        return ResponseEntity.ok(response);
    }
}