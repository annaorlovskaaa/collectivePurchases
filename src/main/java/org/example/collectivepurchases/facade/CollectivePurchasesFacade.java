package org.example.collectivepurchases.facade;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.dtos.*;
import org.example.collectivepurchases.models.PurchasePlan;
import org.example.collectivepurchases.models.User;
import org.example.collectivepurchases.models.composite.Item;
import org.example.collectivepurchases.services.category.CategoryService;
import org.example.collectivepurchases.services.file.FileService;
import org.example.collectivepurchases.services.friendship.FriendshipService;
import org.example.collectivepurchases.services.groupshoppinglist.GroupShoppingListServiceProxy;
import org.example.collectivepurchases.services.item.ItemService;
import org.example.collectivepurchases.services.purchaseplan.PurchasePlanService;
import org.example.collectivepurchases.services.user.AuthService;
import org.example.collectivepurchases.services.user.RegistrationService;
import org.example.collectivepurchases.services.user.UserService;
import org.example.collectivepurchases.services.wishlist.WishListProxy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CollectivePurchasesFacade {
    private final AuthService authService;
    private final CategoryService categoryService;
    private final WishListProxy wishListProxy;
    private final RegistrationService registrationService;
    private final UserService userService;
    private final FileService fileService;
    private final FriendshipService friendshipService;
    private final GroupShoppingListServiceProxy groupShoppingListServiceProxy;
    private final ItemService itemService;
    private final PurchasePlanService purchasePlanService;

    // AuthService methods
    public AuthResponseDto authenticateUser(AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }

    // friendshipService methods
    public boolean addFriendship(FriendshipDto friendshipDto) {
        return friendshipService.addFriendship(friendshipDto);
    }

    public boolean deleteFriendship(Long userId, Long friendId) {
        return friendshipService.deleteFriendship(userId, friendId);
    }

    public List<FriendshipDto> getFriendshipsByUserId(Long userId) {
        return friendshipService.getFriendshipsByUserId(userId);
    }

    // WishListProxy methods
    public WishListDto createWishList(WishListDto wishListDto) {
        return wishListProxy.createWishList(wishListDto);
    }

    public List<WishListDto> getWishListsByUserId(Long userId) {
        return wishListProxy.getWishListsByUserId(userId);
    }

    public WishListDto getWishListById(Long id, String token) {
        return wishListProxy.getWishListById(id, token);
    }

    public void deleteWishListById(Long id) {
        wishListProxy.deleteWishListById(id);
    }

    public WishListDto updateWishList(WishListDto wishListDto) {
        return wishListProxy.updateWishList(wishListDto);
    }

    // RegistrationService methods
    public boolean createUser(UserDto userDTO) {
        return registrationService.createUser(userDTO);
    }

    public boolean isEmailAlreadyInUse(String email) {
        return registrationService.isEmailAlreadyInUse(email);
    }

    // UserService methods
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean userExists(Long userId) {
        return userService.userExists(userId);
    }

    public User getUserByToken(String token) {
        return userService.getUserByToken(token);
    }


    public boolean isFriend(Long userId, Long friendId) {
        return friendshipService.isFriend(userId, friendId);
    }


    // ItemService methods
    public ItemDto createItem(ItemDto itemDto) {
        return itemService.createItem(itemDto);
    }

    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    public ItemDto getItemById(Long itemId) {
        return itemService.getItemById(itemId);
    }

    public ItemDto updateItem(Long itemId, Item item) {
        return itemService.updateItem(itemId, item);
    }

    public void deleteItem(Long itemId) {
        itemService.deleteItem(itemId);
    }

    public Map<String, List<ItemDto>> getItemsGroupedByCategory() {
        return itemService.getItemsGroupedByCategory();
    }

    // PurchasePlanService methods
    public PurchasePlanDto createPurchasePlan(PurchasePlan purchasePlan) {
        return purchasePlanService.createPurchasePlan(purchasePlan);
    }

    public PurchasePlanDto addReceiptToPurchasePlan(Long purchasePlanId, String receiptUrl) {
        return purchasePlanService.addReceiptToPurchasePlan(purchasePlanId, receiptUrl);
    }

    public PurchasePlanDto updateReceiptInPurchasePlan(Long purchasePlanId, String receiptUrl) {
        return purchasePlanService.updateReceiptInPurchasePlan(purchasePlanId, receiptUrl);
    }

    public void deleteReceiptFromPurchasePlan(Long purchasePlanId) {
        purchasePlanService.deleteReceiptFromPurchasePlan(purchasePlanId);
    }

    public List<PurchasePlanDto> getPurchasePlansByGroupShoppingListId(Long groupShoppingListId) {
        return purchasePlanService.getPurchasePlansByGroupShoppingListId(groupShoppingListId);
    }

    public PurchasePlanDto createImportantPurchasePlan(PurchasePlan purchasePlan) {
        return purchasePlanService.createImportantPurchasePlan(purchasePlan);
    }

    public PurchasePlanDto removeImportantPurchasePlan(Long purchasePlanId) {
        return purchasePlanService.removeImportantPurchasePlan(purchasePlanId);
    }

    public Boolean createGroupShoppingList(String token, GroupShoppingListDto groupShoppingListDto) {
        return groupShoppingListServiceProxy.createGroupShoppingList(token, groupShoppingListDto);
    }

    // GroupShoppingListServiceProxy methods
    public List<GroupShoppingListDto> getAllGroupShoppingLists() {
        return groupShoppingListServiceProxy.getAllGroupShoppingLists();
    }

    public GroupShoppingListDto getGroupShoppingListById(Long id, String token) {
        return groupShoppingListServiceProxy.getGroupShoppingListById(id, token);
    }

    public Boolean updateGroupShoppingList(Long id, GroupShoppingListDto groupShoppingListDto, String token) {
        return groupShoppingListServiceProxy.updateGroupShoppingList(id, groupShoppingListDto, token);
    }

    public Boolean deleteGroupShoppingList(Long id, String token) {
        return groupShoppingListServiceProxy.deleteGroupShoppingList(id, token);
    }

    public Boolean addUserToGroupShoppingList(Long groupShoppingListId, Long userId, String token) {
        return groupShoppingListServiceProxy.addUserToGroupShoppingList(groupShoppingListId, userId, token);
    }
}