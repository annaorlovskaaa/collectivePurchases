package org.example.collectivepurchases.services.wishlist;

import org.example.collectivepurchases.dtos.WishListDto;
import org.example.collectivepurchases.models.WishList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishListService {
    WishListDto createWishList(WishListDto wishListDto);

    List<WishListDto> getWishListsByUserId(Long userId);

    WishListDto getWishListById(Long id);

    void deleteWishListById(Long id);

    WishListDto updateWishList(WishListDto wishListDto);
}