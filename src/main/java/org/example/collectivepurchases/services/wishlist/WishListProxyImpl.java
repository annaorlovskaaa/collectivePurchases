package org.example.collectivepurchases.services.wishlist;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.dtos.WishListDto;
import org.example.collectivepurchases.services.friendship.FriendshipService;
import org.example.collectivepurchases.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WishListProxyImpl implements WishListProxy {

    private final WishListService wishListService;
    private final UserService userService;
    private final FriendshipService friendshipService;

    @Override
    public WishListDto createWishList(WishListDto wishListDto) {
        return wishListService.createWishList(wishListDto);
    }

    @Override
    public List<WishListDto> getWishListsByUserId(Long userId) {
        return wishListService.getWishListsByUserId(userId);
    }

    @Override
    public WishListDto getWishListById(Long id, String token) {
        Long currentUserId = userService.getUserByToken(token).getId();
        WishListDto wishList = wishListService.getWishListById(id);
        if (wishList.getUserId().equals(currentUserId) || friendshipService.isFriend(currentUserId, wishList.getUserId())) {
            return wishList;
        } else {
            throw new RuntimeException("Access denied");
        }
    }

    @Override
    public void deleteWishListById(Long id) {
        wishListService.deleteWishListById(id);
    }

    @Override
    public WishListDto updateWishList(WishListDto wishListDto) {
        return wishListService.updateWishList(wishListDto);
    }
}