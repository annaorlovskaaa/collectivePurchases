package org.example.collectivepurchases.services.item;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.dtos.ItemDto;
import org.example.collectivepurchases.models.composite.Category;
import org.example.collectivepurchases.models.composite.Component;
import org.example.collectivepurchases.models.composite.Item;
import org.example.collectivepurchases.repositories.CategoryRepository;
import org.example.collectivepurchases.repositories.ItemRepository;
import org.example.collectivepurchases.services.category.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Category category = categoryService.getOrCreateCategory(itemDto.getCategory());
        Item item = new Item.Builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .category(category)
                .build();
        Item savedItem = itemRepository.save(item);
        return buildItemDto(savedItem);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(ItemServiceImpl::buildItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        return buildItemDto(item);
    }

    @Override
    public ItemDto updateItem(Long itemId, Item item) {
        Item existingItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());
        Item updatedItem = itemRepository.save(existingItem);
        return buildItemDto(updatedItem);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public void addComponentToCategory(Long categoryId, Component component) {
        org.example.collectivepurchases.models.composite.Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        category.add(component);
        categoryRepository.save(category);
    }

    @Override
    public Map<String, List<ItemDto>> getItemsGroupedByCategory() {
        return itemRepository.findAll().stream()
                .collect(Collectors.groupingBy(item -> item.getCategory().getName(),
                        Collectors.mapping(ItemServiceImpl::buildItemDto, Collectors.toList())));
    }

    private static ItemDto buildItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}