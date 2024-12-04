package org.example.collectivepurchases.services.item;

import org.example.collectivepurchases.dtos.ItemDto;
import org.example.collectivepurchases.models.composite.Component;
import org.example.collectivepurchases.models.composite.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);
    List<ItemDto> getAllItems();
    ItemDto getItemById(Long itemId);
    ItemDto updateItem(Long itemId, Item item);
    void deleteItem(Long itemId);
    void addComponentToCategory(Long categoryId, Component component);
    public Map<String, List<ItemDto>> getItemsGroupedByCategory();
}