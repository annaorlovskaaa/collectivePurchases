package org.example.collectivepurchases.services.wishlist;

import org.example.collectivepurchases.dtos.WishListDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WishListProxy {
    WishListDto createWishList(WishListDto wishListDto);
    List<WishListDto> getWishListsByUserId(Long userId);
    WishListDto getWishListById(Long id, String token);
    void deleteWishListById(Long id);
    WishListDto updateWishList(WishListDto wishListDto);
}