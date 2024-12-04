package org.example.collectivepurchases.services.groupshoppinglist;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.dtos.GroupShoppingListDto;
import org.example.collectivepurchases.models.User;
import org.example.collectivepurchases.services.user.UserService;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupShoppingListServiceProxyImpl implements GroupShoppingListServiceProxy {

    private final GroupShoppingListServiceImpl groupShoppingListService;
    private final UserService userService;

    private void checkAccess(Long groupShoppingListId, String token) throws AccessDeniedException {
        User user = userService.getUserByToken(token);
        if (!groupShoppingListService.isUserInGroup(groupShoppingListId, user.getId())) {
            throw new AccessDeniedException("Access denied: User is not a member of the group");
        }
    }

    @Override
    public Boolean createGroupShoppingList(String token, GroupShoppingListDto groupShoppingListDto) {
        return groupShoppingListService.createGroupShoppingList(token, groupShoppingListDto);
    }

    @Override
    public List<GroupShoppingListDto> getAllGroupShoppingLists() {
        return groupShoppingListService.getAllGroupShoppingLists();
    }

    @Override
    public GroupShoppingListDto getGroupShoppingListById(Long id, String token) {
        try {
            checkAccess(id, token);
        } catch (AccessDeniedException e) {
            return null;
        }
        return groupShoppingListService.getGroupShoppingListById(id, token);
    }

    @Override
    public Boolean updateGroupShoppingList(Long id, GroupShoppingListDto groupShoppingListDto, String token) {
        try {
            checkAccess(id, token);
        } catch (AccessDeniedException e) {
            return false;
        }
        return groupShoppingListService.updateGroupShoppingList(id, groupShoppingListDto);
    }

    @Override
    public Boolean deleteGroupShoppingList(Long id, String token) {
        try {
            checkAccess(id, token);
        } catch (AccessDeniedException e) {
            return false;
        }
        return groupShoppingListService.deleteGroupShoppingList(id);
    }

    @Override
    public Boolean addUserToGroupShoppingList(Long groupShoppingListId, Long userId, String token) {
        try {
            checkAccess(userId, token);
        } catch (AccessDeniedException e) {
            return false;
        }
        return groupShoppingListService.addUserToGroupShoppingList(groupShoppingListId, userId);
    }
}