package org.example.collectivepurchases.controllers;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.dtos.ItemDto;
import org.example.collectivepurchases.facade.CollectivePurchasesFacade;
import org.example.collectivepurchases.models.composite.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final CollectivePurchasesFacade collectivePurchasesFacade;

    @PostMapping("/create")
    public ResponseEntity<Object> createItem(@RequestBody ItemDto itemDto) {
        ItemDto createdItem = collectivePurchasesFacade.createItem(itemDto);
        return ResponseEntity.ok().body(Map.of("message", "Item created successfully", "item", createdItem));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllItems() {
        List<ItemDto> items = collectivePurchasesFacade.getAllItems();
        return ResponseEntity.ok(Map.of("items", items));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItemById(@PathVariable Long itemId) {
        ItemDto item = collectivePurchasesFacade.getItemById(itemId);
        return ResponseEntity.ok(Map.of("item", item));
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<Object> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        ItemDto updatedItem = collectivePurchasesFacade.updateItem(itemId, item);
        return ResponseEntity.ok().body(Map.of("message", "Item updated successfully", "item", updatedItem));
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long itemId) {
        collectivePurchasesFacade.deleteItem(itemId);
        return ResponseEntity.ok().body(Map.of("message", "Item deleted successfully"));
    }

    @GetMapping("/grouped-by-category")
    public ResponseEntity<Object> getItemsGroupedByCategory() {
        Map<String, List<ItemDto>> itemsGroupedByCategory = collectivePurchasesFacade.getItemsGroupedByCategory();
        return ResponseEntity.ok(Map.of("itemsGroupedByCategory", itemsGroupedByCategory));
    }
}