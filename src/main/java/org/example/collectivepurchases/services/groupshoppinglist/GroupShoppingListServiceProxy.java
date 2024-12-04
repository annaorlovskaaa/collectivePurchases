package org.example.collectivepurchases.services.groupshoppinglist;

import org.example.collectivepurchases.dtos.GroupShoppingListDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GroupShoppingListServiceProxy {
    Boolean createGroupShoppingList(String token, GroupShoppingListDto groupShoppingListDto);

    List<GroupShoppingListDto> getAllGroupShoppingLists();

    GroupShoppingListDto getGroupShoppingListById(Long id, String token);

    Boolean deleteGroupShoppingList(Long id, String token);

    Boolean addUserToGroupShoppingList(Long groupShoppingListId, Long userId, String token);

    Boolean updateGroupShoppingList(Long id, GroupShoppingListDto groupShoppingListDto, String token);
}