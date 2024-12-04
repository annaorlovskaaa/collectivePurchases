package org.example.collectivepurchases.services.wishlist;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.dtos.WishListDto;
import org.example.collectivepurchases.dtos.WishListItemDto;
import org.example.collectivepurchases.models.WishList;
import org.example.collectivepurchases.models.WishListItem;
import org.example.collectivepurchases.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishlistRepository wishlistRepository;

    @Override
    public WishListDto createWishList(WishListDto wishListDTO) {
        WishList wishList = WishList.builder()
                .name(wishListDTO.getName())
                .userId(wishListDTO.getUserId())
                .build();
        wishList = wishlistRepository.save(wishList);
        return WishListDto.builder()
                .id(wishList.getId())
                .name(wishList.getName())
                .userId(wishList.getUserId())
                .build();
    }

    @Override
    public List<WishListDto> getWishListsByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId).stream()
                .map(wishList -> WishListDto.builder()
                        .id(wishList.getId())
                        .name(wishList.getName())
                        .userId(wishList.getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public WishListDto getWishListById(Long id) {
        WishList wishList = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WishList not found"));
        List<WishListItemDto> itemDtos = wishList.getItems().stream()
                .map(item -> WishListItemDto.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .build())
                .collect(Collectors.toList());
        return WishListDto.builder()
                .id(wishList.getId())
                .name(wishList.getName())
                .userId(wishList.getUserId())
                .items(itemDtos)
                .build();
    }

    @Override
    public void deleteWishListById(Long id) {
        wishlistRepository.deleteById(id);
    }

    @Override
    public WishListDto updateWishList(WishListDto wishListDTO) {
        WishList wishList = wishlistRepository.findById(wishListDTO.getId())
                .orElseThrow(() -> new RuntimeException("WishList not found"));

        List<WishListItem> items = wishListDTO.getItems().stream()
                .map(itemDto -> WishListItem.builder()
                        .name(itemDto.getName())
                        .build())
                .collect(Collectors.toList());

        wishList.setName(wishListDTO.getName());
        wishList.setUserId(wishListDTO.getUserId());
        wishList.setItems(items);

        wishList = wishlistRepository.save(wishList);

        List<WishListItemDto> itemDtos = wishList.getItems().stream()
                .map(item -> WishListItemDto.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .build())
                .collect(Collectors.toList());

        return WishListDto.builder()
                .id(wishList.getId())
                .name(wishList.getName())
                .userId(wishList.getUserId())
                .items(itemDtos)
                .build();
    }
}