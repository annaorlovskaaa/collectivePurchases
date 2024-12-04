package org.example.collectivepurchases.services.groupshoppinglist;

import org.example.collectivepurchases.dtos.GroupShoppingListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupShoppingListService {
    Boolean createGroupShoppingList(String token, GroupShoppingListDto groupShoppingList);

    List<GroupShoppingListDto> getAllGroupShoppingLists();

    GroupShoppingListDto getGroupShoppingListById(Long id, String token);

    Boolean deleteGroupShoppingList(Long id);

    Boolean addUserToGroupShoppingList(Long groupShoppingListId, Long userId);

    Boolean updateGroupShoppingList(Long id, GroupShoppingListDto groupShoppingListDto);

    boolean isUserInGroup(Long groupShoppingListId, Long userId);
}